package com.iluwatar.abstractdocument;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/** Abstract implementation of Document interface. */
public abstract class AbstractDocument implements Document {

  private final Map<String, Object> documentProperties;

  protected AbstractDocument(Map<String, Object> properties) {
    Objects.requireNonNull(properties, "properties map is required");
    this.documentProperties = properties;
  }

  @Override
  public Object put(String key, Object value) {
    return documentProperties.put(key, value);
  }

  @Override
  public Object get(String key) {
    return documentProperties.get(key);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> Stream<T> children(String key, Function<Map<String, Object>, T> childConstructor) {
    return Stream.ofNullable(get(key))
        .filter(Objects::nonNull)
        .map(el -> (List<Map<String, Object>>) el)
        .findAny()
        .stream()
        .flatMap(Collection::stream)
        .map(childConstructor);
  }

  @Override
  public String toString() {
    return buildStringRepresentation();
  }

  private String buildStringRepresentation() {
    var builder = new StringBuilder();
    builder.append(getClass().getName()).append("[");

    Map<String, Object> documentProperties = this.documentProperties;
    int numProperties = documentProperties.size();
    int currentPropertyIndex = 0;

    for (Map.Entry<String, Object> entry : documentProperties.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      builder.append("[").append(key).append(" : ").append(value).append("]");
      if (++currentPropertyIndex < numProperties) {
        builder.append(", ");
      }
    }
    builder.append("]");
    return builder.toString();
  }
}
