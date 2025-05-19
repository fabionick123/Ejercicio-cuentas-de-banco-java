import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Banco{
    static ArrayList<Cuenta> cuentas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        cargarFichero();
        boolean continuar = true;
        
        while (continuar) {
            System.out.println(" 1.Crear Cuenta\n 2.Depositar \n 3.Retirar \n 4.Consultar Saldo \n 5.Consultar Cuentas \n 6.Salir");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                System.out.println("Dime el nombre del titular de la cuenta");
                String titularCuenta = sc.next();
                System.out.println("¿De qué tipo es la cuenta?\n1)Cuenta Corriente.\n2)Cuenta Ahorro");
                int tipo = sc.nextInt();
                if(tipo == 1 ){
                    int numeroDeCuentaAleatorio =  generarNumeroCuenta();
                    CuentaCorriente cuenta = new CuentaCorriente(titularCuenta, numeroDeCuentaAleatorio);
                    cuentas.add(cuenta);
                    System.out.println("Cuenta creada exitosamente, su número de cuenta es "+ numeroDeCuentaAleatorio);
                }
                else if (tipo == 2) {
                    int numeroDeCuentaAleatorio =  generarNumeroCuenta();
                    CuentaAhorro cuenta = new CuentaAhorro(titularCuenta, numeroDeCuentaAleatorio);
                    cuentas.add(cuenta);
                    System.out.println("Cuenta creada exitosamente, su número de cuenta es "+ numeroDeCuentaAleatorio);
                }else{
                    System.out.println("Error al elegir una opción");
                    
                }
                break;
                case 2:
                if (!cuentas.isEmpty()) {
                    System.out.println("¿En qué cuenta quieres ingresar dinero?(Introduce el número del titular)");
                    mostrarCuentas(null);
                    int usuario = sc.nextInt()-1;
                    if(cuentas.get(usuario).titular.equals(cuentas.get(usuario).titular)) {
                        System.out.println("Su saldo actual es ("+ cuentas.get(usuario).saldo +")\n¿Qué monto desea introducir en la cuenta?:");
                        double cantidad = sc.nextInt();
                        Cuenta cuenta = cuentas.get(usuario);
                        cuenta.depositar_dinero(cantidad);
                        System.out.println("Dinero introducido exitosamente.");
                        System.out.println(cuentas.get(usuario).titular+" su saldo actual es ("+ cuentas.get(usuario).saldo +")");
                    }else{
                        System.out.println("El usuario ha sido introducido incorrectamente");
                    }
                }else{
                    System.out.println("No hay ninguna cuenta bancaria registrada.");
                }
                    
                    break;
                case 3:
                System.out.println("¿En qué cuenta quieres retirar dinero?");
                mostrarCuentas(null);
                int retirar = sc.nextInt()-1;
                if (!cuentas.isEmpty()) {    
                    if (cuentas.get(retirar).titular.equals(cuentas.get(retirar).titular)) {
                        Cuenta cuenta = cuentas.get(retirar);
                        if (cuenta instanceof CuentaCorriente) {
                            System.out.println("¿Qué cantidad deseas retirar de tu cuenta?");
                            double cantidad = sc.nextDouble();
                                if (cuentas.get(retirar).saldo - cantidad <= -500) {
                                    System.out.println("Has sobregirado tu cuenta bancaria, no puedes retirar mas dinero");
                                }else{
                                    cuenta.retirar_dinero(cantidad);
                            }
                        }
                        if (cuenta instanceof CuentaAhorro) {
                            System.out.println("¿Qué cantidad deseas retirar de tu cuenta?");
                            double cantidad = sc.nextDouble();
                            if (cuentas.get(retirar).saldo - cantidad >= 0) {
                                if (((CuentaAhorro)cuenta).numeroDeExtracciones <= 3) {
                                    cuenta.retirar_dinero(cantidad);
                                    System.out.println("Tienes "+((CuentaAhorro)cuenta).numeroDeExtracciones+" extracciones, cuando llegues a (3) se te cobrará una comisión.");
                                }else{
                                    cuenta.retirar_dinero(cantidad + 25);
                                    System.out.println("Se te cobrará una comisión de 25 euros a partir de ahora.");
                                }
                            }else{
                                System.out.println("No tienes saldo, no tienes dinero que retirar.");
                            }
                        }
                    }
                }else{
                    System.out.println("No hay ninguna cuenta bancaria registrada.");
                }
                    break;
                case 4:
                System.out.println("¿De qué cuenta te gustaria ver el saldo?");
                mostrarCuentas(null);
                int usuario = sc.nextInt()-1;
                if(cuentas.get(usuario).titular.equals(cuentas.get(usuario).titular)) {
                    System.out.println("Su saldo actual es ("+ cuentas.get(usuario).saldo +")");
                }else{
                        System.out.println("El usuario ha sido introducido incorrectamente");
                }
                    break;
                case 5:
                mostrarCuentas(null);
                    break;
                case 6:
                guardarFichero();
                    continuar = false;
                default:
                    
        }
    }
}
    public static int generarNumeroCuenta(){
        int min = 12903120;
        int max = 99832103;
        int numeroCuenta = (int)(Math.random()*(max - min + 1 )) + min;
        return numeroCuenta;
    }
    public static void mostrarCuentas(Cuenta cuenta){
        for (int i = 0; i < cuentas.size(); i++) {
                System.out.println(i+1+"). Nombre del titular: "+cuentas.get(i).titular +" : Numero de cuenta: "+ cuentas.get(i).numeroDeCuenta);
        }
    }
// Método para guardar todas las cuentas bancarias en un archivo de texto
public static void guardarFichero() throws IOException {
    // Se crea un FileWriter para escribir en el archivo "CuentasBancarias.txt"
    FileWriter fl = new FileWriter("CuentasBancarias.txt");
    
    // Se usa BufferedWriter para mejorar el rendimiento de escritura
    BufferedWriter bf = new BufferedWriter(fl);

    // Se recorre la lista de cuentas bancarias (suponiendo que 'cuentas' es una lista accesible aquí)
    for (Cuenta cuenta : cuentas) {
        // Se determina el tipo de cuenta según su clase (CuentaAhorro o CuentaCorriente)
        String tipoCuenta = cuenta instanceof CuentaAhorro ? "Cuenta Ahorro" : "Cuenta Corriente";

        // Se construye la línea de texto con los datos comunes de la cuenta
        String linea = "Titular: " + cuenta.titular +
            " | Tipo: " + tipoCuenta +
            " | Nº de cuenta: " + cuenta.numeroDeCuenta +
            " | Saldo: " + cuenta.saldo;

        // Si la cuenta es de tipo CuentaAhorro, se añade el número de extracciones
        if (cuenta instanceof CuentaAhorro) {
            linea += " | Extracciones: " + ((CuentaAhorro) cuenta).numeroDeExtracciones;
        }

        // Se escribe la línea en el archivo
        bf.write(linea);

        // Se escribe un salto de línea para separar cada cuenta
        bf.newLine();
    }

    // Se cierra el BufferedWriter para liberar recursos y asegurar que se escriba todo
    bf.close();

    // Mensaje de confirmación en consola
    System.out.println("Cuentas guardadas en 'CuentasBancarias.txt'.");
}

    public static void cargarFichero() {
    try {
        // Creamos el lector del archivo
        FileReader fr = new FileReader("CuentasBancarias.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String linea;

        // Leemos línea por línea el archivo
        while ((linea = br.readLine()) != null) {
            // Cada línea está separada con " | "
            String[] partes = linea.split(" \\| ");

            // Extraemos los datos desde cada parte
            String titular = partes[0].split(": ")[1].trim();
            String tipoCuenta = partes[1].split(": ")[1].trim();
            int numero = Integer.parseInt(partes[2].split(": ")[1].trim());
            double saldo = Double.parseDouble(partes[3].split(": ")[1].trim());

            // Si es Cuenta Ahorro, también leemos extracciones
            if (tipoCuenta.equals("Cuenta Ahorro")) {
                int extracciones = Integer.parseInt(partes[4].split(": ")[1].trim());
                CuentaAhorro ca = new CuentaAhorro(titular, numero);
                ca.saldo = saldo;
                ca.numeroDeExtracciones = extracciones;
                cuentas.add(ca);
            } 
            // Si es Cuenta Corriente
            else if (tipoCuenta.equals("Cuenta Corriente")) {
                CuentaCorriente cc = new CuentaCorriente(titular, numero);
                cc.saldo = saldo;
                cuentas.add(cc);
            }
        }

        // Cerramos el archivo
        br.close();
        fr.close();
        System.out.println("✔ Cuentas cargadas desde el fichero.");
    } catch (IOException e) {
        System.out.println("Error al leer el fichero: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error de formato: " + e.getMessage());
    }
}

}