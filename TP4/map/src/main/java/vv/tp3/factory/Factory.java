package vv.tp3.factory;


public interface Factory<O, T> {

    //transform T in O
    public O transform(T object);

    public O create();
}
