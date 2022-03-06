package mall.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();
}