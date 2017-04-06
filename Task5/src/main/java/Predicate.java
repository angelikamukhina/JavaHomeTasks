public abstract class Predicate<T> {
    public static final Predicate ALWAYS_TRUE = new Predicate() {
        @Override
        public Boolean apply(Object arg) {
            return true;
        }
    };

    public static final Predicate ALWAYS_FALSE = new Predicate() {
        @Override
        public Boolean apply(Object arg) {
            return false;
        }
    };

    public abstract Boolean apply(T arg);

    public Predicate<T> or(Predicate<T> p) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T arg) {
                return Predicate.this.apply(arg) || p.apply(arg);
            }
        };
    }

    public Predicate<T> and(Predicate<T> p) {
        return new Predicate<T>() {
            public Boolean apply(T arg) {
                return Predicate.this.apply(arg) && p.apply(arg);
            }
        };
    }

    public Predicate<T> not() {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T arg) {
                return !Predicate.this.apply(arg);
            }
        };
    }
}
