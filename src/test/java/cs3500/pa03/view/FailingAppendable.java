package cs3500.pa03.view;

import java.io.IOException;

/**
 * Appendable that fails for testing purposes
 */
public class FailingAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new RuntimeException("Append operation failed.");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new RuntimeException("Append operation failed.");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new RuntimeException("Append operation failed.");
  }
}
