import java.time.LocalDate;

public class Cuenta
{
    // Código único de la cuenta, generado automáticamente
    private String codCuenta = "cta-" + cantidadCuentasCreadas;

    // Saldo actual de la cuenta
    private double saldo;

    // Nombre del titular de la cuenta
    private String nombreCuentaHabiente;

    // Fecha en que se creó la cuenta
    private String fechaCreacion;

    // Número de depósitos realizados en la cuenta
    private int cantDepositosRealizados;

    // Número de retiros exitosos realizados en la cuenta
    private int cantRetirosExitososRealizados;

    // Contador estático de cuentas creadas
    static int cantidadCuentasCreadas = 0;

    // Constructor principal que recibe nombre del titular y saldo inicial
    public Cuenta(String nombreCuentaHabiente , double pSaldo){
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        saldo = pSaldo;
        cantidadCuentasCreadas++;
        codCuenta = "cta-" + cantidadCuentasCreadas;
        fechaCreacion = LocalDate.now().toString();
    }

    // Constructor alternativo que asigna nombre por defecto
    public Cuenta(double pSaldo) {
        this("NULL", pSaldo);
    }

    // Retorna el nombre del titular de la cuenta
    public String getNombreCuentaHabiente() {
        return nombreCuentaHabiente;
    }

    // Asigna un nuevo nombre al titular de la cuenta
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        this.nombreCuentaHabiente = pNombreCuentaHabiente;
    }

    // Retorna el código único de la cuenta
    public String getCodCuenta() {
        return codCuenta;
    }

    // Retorna el saldo actual de la cuenta
    public double getSaldo() {
        return saldo;
    }

    // Realiza un depósito en la cuenta y actualiza el saldo
    public double depositar(double monto) {
        cantDepositosRealizados += 1;
        saldo += monto;
        return saldo;
    }

    // Realiza un retiro si el monto es válido y actualiza el saldo
    public double retirar(double monto) {
        if (validarRetiro(monto)) {
            saldo -= monto;
            cantRetirosExitososRealizados += 1;
        }
        return saldo;
    }

    // Verifica si el monto a retirar es menor o igual al saldo disponible
    private boolean validarRetiro(double monto) {
        return monto <= saldo;
    }

    // Retorna la cantidad total de cuentas creadas
    static int getCantCuentasCreadas() {
        return cantidadCuentasCreadas;
    }

    // Retorna una representación textual de la cuenta
    public String toString() {
        return "Cuenta{" +
               "codCuenta='" + codCuenta + '\'' +
               ", nombreCuentaHabiente='" + nombreCuentaHabiente + '\'' +
               ", saldo=" + saldo +
               ", fechaCreacion='" + fechaCreacion + '\'' +
               ", cantDepositosRealizados=" + cantDepositosRealizados +
               ", cantRetirosExitososRealizados=" + cantRetirosExitososRealizados +
               '}';
    }
}