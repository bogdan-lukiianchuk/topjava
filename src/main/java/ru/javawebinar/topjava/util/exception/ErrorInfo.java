package ru.javawebinar.topjava.util.exception;

public class ErrorInfo {
    private final String url;
    private final String cause;
    private final String detail;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
    }

    public ErrorInfo(CharSequence url, Throwable e, CharSequence detail) {
        this.url = url.toString();
        this.cause = e.getClass().getSimpleName();
        this.detail = detail.toString();
    }

    public ErrorInfo(CharSequence url, CharSequence cause, CharSequence detail) {
        this.url = url.toString();
        this.cause = cause.toString();
        this.detail = detail.toString();
    }

}