package neqsim.process.util;

import java.util.List;
import java.util.ArrayList;

public final class RingBuffer<T> {
  private final Object[] buffer;
  private int index = 0;
  private int size = 0;

  // Overwrite tracking
  private int lastReadIndex = 0;
  private long cycleCount = 0;
  private long lastReadCycle = 0;

  public RingBuffer(int capacity) {
    buffer = new Object[capacity];
  }

  public void add(T element) {
    buffer[index] = element;
    index = (index + 1) % buffer.length;

    if (size < buffer.length) {
      size++;
    } else if (index == 0) {
      cycleCount++;
    }
  }

  @SuppressWarnings("unchecked")

  public T get(int i) {

    if (i < 0 || i >= size)
      throw new IndexOutOfBoundsException();
    int realIndex = (index - size + i + buffer.length) % buffer.length;
    return (T) buffer[realIndex];
  }

  public int size() {
    return size;
  }

  public int capacity() {
    return buffer.length;
  }

  public void clear() {
    index = 0;
    size = 0;
    cycleCount = 0;
    lastReadIndex = 0;
    lastReadCycle = 0;
  }

  /**
   * Returns the full buffer.
   */
  public List<T> toList() {
    List<T> out = new ArrayList<>(size);
    for (int i = 0; i < size; i++)
      out.add(get(i));
    return out;
  }

  /**
   * Returns only new entries added since the last call. If the buffer wrapped, it automatically
   * returns the new segment.
   */
  @SuppressWarnings("unchecked")
  public List<T> getNewEntries() {
    List<T> result = new ArrayList<>();

    if (size == 0)
      return result;

    if (cycleCount > lastReadCycle || index > lastReadIndex) {
      int start = lastReadIndex;
      int end = index;
      int cap = buffer.length;

      // If the buffer wrapped around since last read
      if (cycleCount > lastReadCycle && size == cap) {
        for (int i = start; i < cap; i++) {
          result.add((T) buffer[i]);
        }
        for (int i = 0; i < end; i++) {
          result.add((T) buffer[i]);
        }
      } else {
        for (int i = start; i < end; i++) {
          result.add((T) buffer[i]);
        }
      }
    }

    // Update tracking
    lastReadIndex = index;
    lastReadCycle = cycleCount;
    return result;
  }
}

