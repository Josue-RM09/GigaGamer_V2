package info.gigagamer.controlador;

import info.gigagamer.modelo.Producto;
import info.gigagamer.modelo.Usuario;
import info.gigagamer.servicio.ProductoService;
import info.gigagamer.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BienvenidaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/bienvenido")
    public String mostrarBienvenida(@AuthenticationPrincipal User user, Model model) {

        Optional<Usuario> usuarioOpt = usuarioService.findByEmail(user.getUsername());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            String tipoUsuario = usuario.getTipoUsuario();

            model.addAttribute("nombre", usuario.getNombre());
            model.addAttribute("tipoUsuario", tipoUsuario);
            List<Producto> productos = productoService.obtenerTodosLosProductos();
            model.addAttribute("productos", productos);

            return "bienvenido";
        } else {
            model.addAttribute("error", "Usuario no encontrado");
            return "error";
        }
    }
}
