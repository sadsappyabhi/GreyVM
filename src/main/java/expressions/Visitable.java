package expressions;

public interface Visitable {
    void accept(Visitor v);
}
