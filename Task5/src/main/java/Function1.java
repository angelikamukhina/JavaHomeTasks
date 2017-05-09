public abstract class Function1<T, R> {

    public abstract R apply(T arg);

    public <S> Function1<T, S> compose(final Function1<? super R, ? extends S> g) {
        return new Function1<T, S>() {
            public S apply(T arg) {
                return g.apply(Function1.this.apply(arg));
            }
        };
    }
}
