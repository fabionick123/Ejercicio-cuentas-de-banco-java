public class CuentaCorriente extends Cuenta{

    public CuentaCorriente(String titular, int numeroDeCuenta) {
        super(titular, numeroDeCuenta);
    }

    @Override
    public void depositar_dinero(double cantidad) {
        saldo += cantidad;
    }

    @Override
    public void retirar_dinero(double cantidad) {
        saldo -= cantidad;
    }
    
}
