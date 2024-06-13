package compiler.scan;

public enum ScannerState {
    start,
    identifier,
    number,
    colon,
    symbol,
    end,
    fail,
}
