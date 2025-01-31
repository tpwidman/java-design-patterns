/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.fluentinterface.fluentiterable.lazy;

import java.util.Iterator;

/**
 * This class is used to realize LazyFluentIterables. It decorates a given iterator. Does not
 * support consecutive hasNext() calls.
 */
public abstract class DecoratingIterator<TYPE> implements Iterator<TYPE> {

  protected final Iterator<TYPE> fromIterator;

  private TYPE next;

  /**
   * Creates an iterator that decorates the given iterator.
   */
  public DecoratingIterator(Iterator<TYPE> fromIterator) {
    this.fromIterator = fromIterator;
  }

  /**
   * Precomputes and saves the next element of the Iterable. null is considered as end of data.
   * 
   * @return true if a next element is available
   */
  @Override
  public final boolean hasNext() {
    next = computeNext();
    return next != null;
  }

  /**
   * Returns the next element of the Iterable.
   * 
   * @return the next element of the Iterable, or null if not present.
   */
  @Override
  public final TYPE next() {
    if (next == null) {
      return fromIterator.next();
    } else {
      final TYPE result = next;
      next = null;
      return result;
    }
  }

  /**
   * Computes the next object of the Iterable. Can be implemented to realize custom behaviour for an
   * iteration process. null is considered as end of data.
   * 
   * @return the next element of the Iterable.
   */
  public abstract TYPE computeNext();
}
