public abstract class Function2<T1, T2, R> {
    public abstract R apply(final T1 arg1, final T2 arg2);

    public <S> Function2<T1, T2, S> compose(final Function1<? super R, ? extends S> g) {
        return new Function2<T1, T2, S>() {
            @Override
            public S apply(final T1 arg1, final T2 arg2) {
                return g.apply(Function2.this.apply(arg1, arg2));
            }
        };
    }

    public Function1<T2, R> bind1(final T1 arg1) {
        return new Function1<T2, R>() {
            @Override
            public R apply(final T2 arg2) {
                return Function2.this.apply(arg1, arg2);
            }
        };
    }

    public Function1<T1, R> bind2(final T2 arg2) {
        return new Function1<T1, R>() {
            @Override
            public R apply(final T1 arg1) {
                return Function2.this.apply(arg1, arg2);
            }
        };
    }

    public Function1<T1, Function1<T2, R>> curry() {
        return new Function1<T1, Function1<T2, R>>() {
            @Override
            public Function1<T2, R> apply(final T1 arg1) {
                return Function2.this.bind1(arg1);
            }
        };
    }
}


