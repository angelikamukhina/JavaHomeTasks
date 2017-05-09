public abstract class Predicate<T> {
    public static final Predicate<Object> ALWAYS_TRUE = new Predicate<Object>() {
        @Override
        public Boolean apply(final Object arg) {
            return true;
        }
    };

    public static final Predicate<Object> ALWAYS_FALSE = new Predicate<Object>() {
        @Override
        public Boolean apply(final Object arg) {
            return false;
        }
    };

    public abstract Boolean apply(final T arg);

    public Predicate<T> or(final Predicate<? super T> predicate) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(final T arg) {
                return Predicate.this.apply(arg) || predicate.apply(arg);
            }
        };
    }

    public Predicate<T> and(final Predicate<? super T> predicate) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(final T arg) {
                return Predicate.this.apply(arg) && predicate.apply(arg);
            }
        };
    }

    public Predicate<T> not() {
        return new Predicate<T>() {
            @Override
            public Boolean apply(final T arg) {
                return !Predicate.this.apply(arg);
            }
        };
    }
}
