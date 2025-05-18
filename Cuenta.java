public abstract class Cuenta implements cuentas{
    protected int numeroDeCuenta;
    protected String titular;
    protected double saldo = 0;
    public Cuenta(String titular, int numeroDeCuenta){
        this.titular = titular;
        this.numeroDeCuenta = numeroDeCuenta;
    }
}
