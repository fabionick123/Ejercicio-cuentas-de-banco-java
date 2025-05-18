public class CuentaAhorro extends Cuenta{
    public int numeroDeExtracciones = 0;
    public CuentaAhorro(String titular, int numeroDeCuenta) {
        super(titular, numeroDeCuenta);
    }
    @Override
    public void depositar_dinero(double cantidad) {
        saldo += cantidad;
    }
    @Override
    public void retirar_dinero(double cantidad) {
        saldo -= cantidad;
        numeroDeExtracciones++;
    }
    
}
