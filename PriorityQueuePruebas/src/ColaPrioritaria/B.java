package ColaPrioritaria;


import java.util.Objects;

public class B {
    A a = new A();
    private B boleano;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        B b = (B) o;

        return Objects.equals(boleano, b.boleano);
    }
    private void test(){

    }
    @Override
    public int hashCode() {
        return Objects.hash(primero,segundo,tercero,cuarto);

    }

}
