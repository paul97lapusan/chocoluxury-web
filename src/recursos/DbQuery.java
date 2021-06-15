package recursos;

public class DbQuery {
		// clientes
	private static final String InsertarCliente = "insert into clientes(nombre, telefono, direccion, email, clave) values (?,?,?,?,?)";
	private static final String ModificarCliente = "update clientes set nombre=?, telefono=?, direccion=?, email=?, clave=? where cod_cli=?";
	private static final String BorrarCliente = "delete from clientes where cod_cli =?";
	private static final String RecuperarClienteById = "select cod_cli, nombre, ifnull(telefono,''), ifnull(direccion,''), email, clave from clientes where cod_cli=?";
	private static final String RecuperarCliente = "select cod_cli, nombre, ifnull(telefono,''), ifnull(direccion,''), email, clave from clientes where email=?";
	private static final String RecuperarTodosCliente = "select cod_cli, nombre, ifnull(telefono, ''), ifnull(direccion, ''), email, clave from clientes order by nombre";
	private static final String RecuperarClienteBloqueo = "select cod_cli, nombre, ifnull(telefono,''), ifnull(direccion,''), email, clave from clientes where cod_cli=? for update";
	private static final String ValidarLogin = "select email, clave from clientes where email=? and clave=? UNION "
												+ "select email, clave from empleados where email=? and clave=?";
	private static final String ModificarClienteConcurrente ="update clientes "+
		    " set NOMBRE=?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	        " TELEFONO=?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
	        " DIRECCION =?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	        " EMAIL =?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
	        " CLAVE =?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	   " where  cod_cli= ? and "+
	       " NOMBRE=? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
	       " nvl(TELEFONO,'null') =? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
	       " nvl(DIRECCION, 'null') =? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
	       " EMAIL =? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
	       " CLAVE =?" ;
	private static final String ExisteEmail = "select email from clientes where email=? UNION select email from empleados where email=?";
	private static final String EsCliente = "select cod_cli, nombre, ifnull(telefono,''), ifnull(direccion,''), email, clave from clientes where email=?";
	
	
	// empleados
	private static final String InsertarEmpleado = "insert into empleados (cod_emp, dni, email, clave, nombre, telefono, perfil) values (0,?,?,?,?,?,?)";
	private static final String RecuperarEmpleadoByEmail = "select cod_emp, dni, email, clave, nombre, ifnull(telefono, ''), perfil from empleados where email=?";
	private static final String RecuperarEmpleadoById = "select cod_emp, dni, email, clave, nombre, ifnull(telefono, ''), perfil from empleados where cod_emp=?";
	private static final String BorrarEmpleado = "delete from empleados where cod_emp=?";
	private static final String ModificarEmpleado = "update empleados set dni=?, email=?, clave=?, nombre=?, telefono=?, perfil=? where cod_emp=?";
	private static final String RecuperarTodosEmpleado = "select a.cod_emp, a.dni, a.email, a.clave, a.nombre, a.telefono, a.perfil, b.perfil from empleados a, perfiles_empleado b where a.perfil=b.id_perfil order by a.perfil";
	// articulos
	private static final String RecuperarTodosArticulo = "select cod_art, nombre_art, descripcion, precio_venta, stock, imagen from articulos order by nombre_art";
	private static final String RecuperarArticuloById = "select cod_art, nombre_art, descripcion, precio_venta, stock, imagen from articulos where cod_art=?";
	private static final String InsertarArticulo = "insert into articulos (cod_art, nombre_art, descripcion, precio_venta, stock, imagen) values (0,?,?,?,?,?)";
	private static final String BorrarArticulo = "delete from articulos where cod_art=?";
	private static final String ModificarArticulo = "update articulos set nombre_art=?, descripcion=?, precio_venta=?, stock=?, imagen=? where cod_art=?";
	//pedidos
	private static final String InsertarPedido = "insert into pedidos(cod_ped, fecha_ped, cod_cli, estado, domicilio, importe) values (0,?,?,?,?,?)";
	private static final String RecuperarUltimoPedido = "select cod_ped, fecha_ped, cod_cli, estado, domicilio, importe from pedidos order by cod_ped desc limit 1";
	private static final String RecuperarTodosPedido = "select cod_ped, fecha_ped, cod_cli, estado, domicilio, importe from pedidos order by estado";
	private static final String RecuperarPedidoById = "select cod_ped, fecha_ped, cod_cli, estado, domicilio, importe from pedidos where cod_ped=?";
	private static final String ModificarPedido = "update pedidos set fecha_ped=?, cod_cli=?, estado=?, domicilio=?, importe=? where cod_ped=?";
	//lin_ped
	private static final String InsertarLinPed = "insert into lin_ped(cod_ped, cod_art, cantidad_pedida) values (?,?,?)";
	private static final String RecuperarTodosLinPedById = "select cod_ped, cod_art, cantidad_pedida from lin_ped where cod_ped=?";
	//estado_pedido
	private static final String RecuperarEstadoPedidoById = "select id_estado, estado from estados_pedido where id_estado=?";



	
	
	public static String getInsertarCliente() {
		return InsertarCliente;
	}
	
	public static String getModificarCliente() {
		return ModificarCliente;
	}
	
	public static String getBorrarCliente() {
		return BorrarCliente;
	}
	
	public static String getRecuperarCliente() {
		return RecuperarCliente;
	}
	
	public static String getRecuperarTodosCliente() {
		return RecuperarTodosCliente;
	}
	
	public static String getModificarClienteConcurrente() {
		return ModificarClienteConcurrente;
	}

	public static String getRecuperarClienteBloqueo() {
		return RecuperarClienteBloqueo;
	}

	public static String getValidarLogin() {
		return ValidarLogin;
	}

	public static String getExisteEmail() {
		// TODO Auto-generated method stub
		return ExisteEmail;
	}

	public static String getEsCliente() {
		// TODO Auto-generated method stub
		return EsCliente;
	}

	public static String getRecuperarEmpleadoByEmail() {
		// TODO Auto-generated method stub
		return RecuperarEmpleadoByEmail;
	}

	public static String getRecuperarTodosArticulo() {
		// TODO Auto-generated method stub
		return RecuperarTodosArticulo;
	}

	public static String getModificarEmpleado() {
		// TODO Auto-generated method stub
		return ModificarEmpleado;
	}

	public static String getRecuperarArticuloById() {
		// TODO Auto-generated method stub
		return RecuperarArticuloById;
	}

	public static String getRecuperarTodosEmpleado() {
		// TODO Auto-generated method stub
		return RecuperarTodosEmpleado;
	}

	public static String getRecuperarEmpleadoById() {
		// TODO Auto-generated method stub
		return RecuperarEmpleadoById;
	}

	public static String getBorrarEmpleado() {
		// TODO Auto-generated method stub
		return BorrarEmpleado;
	}

	public static String getInsertarPedido() {
		// TODO Auto-generated method stub
		return InsertarPedido;
	}

	public static String getInsertarLinPed() {
		// TODO Auto-generated method stub
		return InsertarLinPed;
	}

	public static String getRecuperarUltimoPedido() {
		// TODO Auto-generated method stub
		return RecuperarUltimoPedido;
	}

	public static String getInsertarEmpleado() {
		// TODO Auto-generated method stub
		return InsertarEmpleado;
	}

	public static String getInsertarArticulo() {
		// TODO Auto-generated method stub
		return InsertarArticulo;
	}

	public static String getBorrarArticulo() {
		// TODO Auto-generated method stub
		return BorrarArticulo;
	}

	public static String getModificarArticulo() {
		// TODO Auto-generated method stub
		return ModificarArticulo;
	}

	public static String getRecuperarTodosPedido() {
		// TODO Auto-generated method stub
		return RecuperarTodosPedido;
	}

	public static String getRecuperarClienteById() {
		// TODO Auto-generated method stub
		return RecuperarClienteById;
	}

	public static String getRecuperarEstadoPedidoById() {
		// TODO Auto-generated method stub
		return RecuperarEstadoPedidoById;
	}

	public static String getRecuperarPedidoById() {
		// TODO Auto-generated method stub
		return RecuperarPedidoById;
	}

	public static String getModificarPedido() {
		// TODO Auto-generated method stub
		return ModificarPedido;
	}

	public static String getRecuperarTodosLinPedById() {
		// TODO Auto-generated method stub
		return RecuperarTodosLinPedById;
	}

	
}
