package com.iluwatar.abstractdocument;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/** Document interface. */
public interface Document {

  /**
   * Puts the value related to the key.
   *
   * @param key element key
   * @param value element value
   * @return previous value associated with the key or null if none
   */
  Object put(String key, Object value);

  /**
   * Gets the value for the key.
   *
   * @param <T> expected type
   * @param key element key
   * @return value or null
   */
  <T> T get(String key);

  /**
   * Gets the stream of child documents.
   *
   * @param key element key
   * @param constructor constructor of child class
   * @return stream of child documents or empty stream if none
   */
  <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor);

  /**
   * Checks if the document contains the key.
   *
   * @param key element key
   * @return true if key exists
   */
  default boolean containsKey(String key) {
    return get(key) != null;
  }
}
