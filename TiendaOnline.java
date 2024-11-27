import java.util.Scanner;

public class TiendaOnline {

    /*
    Escribe un programa en Java que simule una tienda online.
    El programa debe permitir al usuario agregar productos a un carrito de compras, ver el total de la compra y finalizar la compra.
    Cada producto tiene un nombre y un precio. El programa debe mostrar una lista de productos disponibles para que el usuario elija.
     */
// NUEBA PRUEBA COMMIT

    private static final int MAX_PRODUCTOS = 10;
    private static String[] nombresProductos = new String[MAX_PRODUCTOS];
    private static double[] preciosProductos = new double[MAX_PRODUCTOS];
    private static int[] cantidadesCarrito = new int[MAX_PRODUCTOS];
    private static int cantidadProductos = 0;

    public static void main(String[] args) {
        inicializarProductos();

        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcionUsuario(scanner);

            switch (opcion) {
                case 1:
                    mostrarProductosDisponibles();
                    break;
                case 2:
                    agregarProductoAlCarrito(scanner);
                    break;
                case 3:
                    mostrarTotalCompra();
                    break;
                case 4:
                    System.out.println("Gracias por comprar. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    // Método para inicializar la lista de productos
    private static void inicializarProductos() {
        agregarProducto("Producto A", 10.0);
        agregarProducto("Producto B", 20.0);
        agregarProducto("Producto C", 30.0);
        // Puedes agregar más productos según sea necesario
    }

    // Método para agregar un producto a la lista
    private static void agregarProducto(String nombre, double precio) {
        if (cantidadProductos < MAX_PRODUCTOS) {
            nombresProductos[cantidadProductos] = nombre;
            preciosProductos[cantidadProductos] = precio;
            cantidadProductos++;
        } else {
            System.out.println("No es posible agregar más productos. Límite alcanzado.");
        }
    }

    // Método para mostrar el menú
    private static void mostrarMenu() {
        System.out.println("----------- Menú -----------");
        System.out.println("1. Mostrar productos disponibles");
        System.out.println("2. Agregar producto al carrito");
        System.out.println("3. Mostrar total de la compra");
        System.out.println("4. Finalizar la compra");
        System.out.print("Seleccione una opción: ");
    }

    // Método para obtener la opción del usuario
    private static int obtenerOpcionUsuario(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            scanner.next(); // Consumir la entrada no válida
        }
        return scanner.nextInt();
    }

    // Método para mostrar los productos disponibles
    private static void mostrarProductosDisponibles() {
        System.out.println("----------- Productos Disponibles -----------");
        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println((i + 1) + ". " + nombresProductos[i] + " - Precio: $" + preciosProductos[i]);
        }
    }

    // Método para agregar un producto al carrito
    private static void agregarProductoAlCarrito(Scanner scanner) {
        mostrarProductosDisponibles();
        System.out.print("Ingrese el número del producto que desea agregar al carrito: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            scanner.next(); // Consumir la entrada no válida
        }

        int numeroProducto = scanner.nextInt();

        if (numeroProducto >= 1 && numeroProducto <= cantidadProductos) {
            System.out.print("Ingrese la cantidad que desea comprar: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.next(); // Consumir la entrada no válida
            }

            int cantidadCompra = scanner.nextInt();
            int indiceProducto = numeroProducto - 1;

            if (cantidadesCarrito[indiceProducto] + cantidadCompra <= 99) {
                cantidadesCarrito[indiceProducto] += cantidadCompra;
                System.out.println("Producto agregado al carrito correctamente.");
            } else {
                System.out.println("No se pueden comprar más de 99 unidades del mismo producto.");
            }

        } else {
            System.out.println("Número de producto no válido. Por favor, seleccione un número de producto válido.");
        }
    }

    // Método para mostrar el total de la compra
    private static void mostrarTotalCompra() {
        System.out.println("----------- Carrito de Compras -----------");
        double total = 0.0;
        for (int i = 0; i < cantidadProductos; i++) {
            if (cantidadesCarrito[i] > 0) {
                double subtotal = cantidadesCarrito[i] * preciosProductos[i];
                System.out.println(nombresProductos[i] + " - Cantidad: " + cantidadesCarrito[i] + " - Subtotal: $" + subtotal);
                total += subtotal;
            }
        }
        System.out.println("Total de la compra: $" + total);
    }

}
