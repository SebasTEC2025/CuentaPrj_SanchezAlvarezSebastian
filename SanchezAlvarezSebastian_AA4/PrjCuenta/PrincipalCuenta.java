import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalCuenta {
    public static void main(String[] args) {
        // Se crea el scanner para leer entradas del usuario
        Scanner sc = new Scanner(System.in);

        // Lista que almacena todas las cuentas creadas
        List<Cuenta> cuentas = new ArrayList<>();

        // Índice de la cuenta actualmente seleccionada
        int actual = -1;

        // Encabezado del programa
        System.out.println("======================================");
        System.out.println("   CLI de Prueba - Clase Cuenta");
        System.out.println("======================================");

        // Bucle principal del menú
        boolean salir = false;
        while (!salir) {
            // Menú de opciones
            System.out.print("");
            System.out.println("\nMenú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de Cuentas Creadas");
            System.out.println("3) Listar Cuentas");
            System.out.println("4) Seleccionar Cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Consultar Saldo");
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            System.out.print("");
            String op = sc.nextLine().trim();

            // Evaluación de la opción seleccionada
            switch (op) {
                case "1": {
                    // Crea una nueva cuenta con nombre y saldo inicial
                    System.out.print("Nombre del cuentahabiente: ");
                    String nombre = sc.nextLine().trim();

                    System.out.print("Saldo inicial (presiona Enter para usar 0.0): ");
                    String entrada = sc.nextLine().trim();

                    Cuenta cuenta;
                    if (entrada.isEmpty()) {
                        cuenta = new Cuenta(nombre, 0.0);
                    } else {
                        double saldo;
                        try {
                            saldo = Double.parseDouble(entrada);
                        } catch (NumberFormatException e) {
                            System.out.println("Número inválido, se usará 0.0");
                            saldo = 0.0;
                        }
                        cuenta = new Cuenta(nombre, saldo);
                    }

                    cuentas.add(cuenta);
                    actual = cuentas.size() - 1;

                    System.out.println("Cuenta creada y seleccionada (índice " + actual + ").");
                    System.out.println("Código asignado: " + cuenta.getCodCuenta());
                    break;
                }

                case "2": {
                    // Muestra la cantidad total de cuentas creadas
                    int total = Cuenta.getCantCuentasCreadas();
                    if (total == 0) {
                        System.out.println("Aún no se ha creado ninguna cuenta.");
                    } else {
                        System.out.println("Total de cuentas creadas: " + total);
                    }
                    break;
                }

                case "3": {
                    // Lista todas las cuentas registradas
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas registradas aún.");
                    } else {
                        System.out.println("Listado de cuentas:");
                        System.out.println("Índice | Código     | Saldo   | Titular");
                        for (int i = 0; i < cuentas.size(); i++) {
                            Cuenta c = cuentas.get(i);
                            System.out.printf("  %d    | %-10s | %.2f | %s%n",
                                i,
                                c.getCodCuenta(),
                                c.getSaldo(),
                                c.getNombreCuentaHabiente());
                        }
                    }
                    break;
                }

                case "4": {
                    // Permite seleccionar una cuenta por índice
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas disponibles para seleccionar.");
                        break;
                    }
                    System.out.print("Ingrese el índice de la cuenta a seleccionar: ");
                    String entrada = sc.nextLine().trim();
                    try {
                        int idx = Integer.parseInt(entrada);
                        if (idx >= 0 && idx < cuentas.size()) {
                            actual = idx;
                            System.out.println("Cuenta seleccionada: " + cuentas.get(actual).getCodCuenta());
                        } else {
                            System.out.println("Índice fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Debe ingresar un número.");
                    }
                    break;
                }

                case "5": {
                    // Realiza un depósito en la cuenta seleccionada
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("No hay cuenta activa. Cree y seleccione una primero.");
                        break;
                    }
                    System.out.print("Monto a depositar: ");
                    String entrada = sc.nextLine().trim();
                    try {
                        double monto = Double.parseDouble(entrada);
                        if (monto <= 0) {
                            System.out.println("El monto debe ser mayor que cero.");
                        } else {
                            cuentas.get(actual).depositar(monto);
                            System.out.println("Depósito realizado. Nuevo saldo: " + cuentas.get(actual).getSaldo());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Debe ingresar un número.");
                    }
                    break;
                }

                case "6": {
                    // Realiza un retiro en la cuenta seleccionada
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("No hay cuenta activa. Cree y seleccione una primero.");
                        break;
                    }
                    System.out.print("Monto a retirar: ");
                    String entrada = sc.nextLine().trim();
                    try {
                        double monto = Double.parseDouble(entrada);
                        if (monto <= 0) {
                            System.out.println("El monto debe ser mayor que cero.");
                        } else if (monto > cuentas.get(actual).getSaldo()) {
                            System.out.println("Retiro no permitido. El monto excede el saldo disponible.");
                        } else {
                            cuentas.get(actual).retirar(monto);
                            System.out.println("Retiro exitoso. Nuevo saldo: " + cuentas.get(actual).getSaldo());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Debe ingresar un número.");
                    }
                    break;
                }

                case "7": {
                    // Muestra el saldo actual de la cuenta seleccionada
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("No hay cuenta activa. Cree y seleccione una primero.");
                        break;
                    }
                    System.out.printf("Saldo actual: %.2f%n", cuentas.get(actual).getSaldo());
                    break;
                }

                case "8": {
                    // Muestra el estado completo de la cuenta seleccionada
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("No hay cuenta activa. Cree y seleccione una primero.");
                        break;
                    }
                    System.out.println("Estado completo de la cuenta:");
                    System.out.println(cuentas.get(actual).toString());
                    break;
                }

                case "0": {
                    // Finaliza el programa
                    salir = true;
                    System.out.println("Programa finalizado.");
                    break;
                }

                default: {
                    // Opción inválida
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
                }
            }
        }
        // Cierra el scanner al finalizar
        sc.close();
    }
}
