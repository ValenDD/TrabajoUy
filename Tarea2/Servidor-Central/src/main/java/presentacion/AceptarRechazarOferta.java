package main.java.presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.java.datatypes.DtOfertaLaboral;
import main.java.datatypes.EstadoOferta;
import main.java.excepciones.OfertaLaboralNoExisteException;
import main.java.excepciones.UsuarioNoExisteException;
import main.java.interfaces.IcontroladorOferta;
import main.java.interfaces.IcontroladorUsuario;

/**
 * Clase AceptarRechazarOferta .
 */

@SuppressWarnings("serial")
public class AceptarRechazarOferta extends JInternalFrame {

  // Controlador de usuarios que se utilizará para
  // las acciones del JFrame
  private IcontroladorOferta controladorOfertaLaboral;
  private IcontroladorUsuario controladorUsuario;
  private JTextField textFieldHorarioOferta;
  private JTextField textFieldRemuneracion;
  private JTextField textFieldCiudad;
  private JTextField textFieldDepartamento;
  private JComboBox<String> comboBoxSeleccionUsuario;
  private JComboBox<String> comboBoxSeleccionOferta;
  private String ofertaSeleccionada;
  private JButton btnConfirmar;
  private JButton btnRechazar;
  private JButton btnCerrar;
  private JPanel panelDatos;

  /**
   * Create the frame.
   */
  public AceptarRechazarOferta(
        IcontroladorOferta icontOfeLab,
          IcontroladorUsuario icontUsuLab) {
    // Se inicializa con el controlador de oferta
    controladorOfertaLaboral = icontOfeLab;
    controladorUsuario = icontUsuLab;
    this.ofertaSeleccionada = "";

    // Propiedades del JInternalFrame como dimensión,
    // posición dentro del frame,
    // etc.
    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Aceptar/Rechazar Oferta laboral");
    setBounds(30, 30, 713, 380);

    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(
          new FlowLayout(FlowLayout.CENTER, 120, 20));

    this.btnConfirmar = new JButton("Confirmar");
    btnConfirmar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        confirmarOfertaLaboral();

      }
    });
    panelBotones.add(btnConfirmar);

    this.btnRechazar = new JButton("Rechazar");
    btnRechazar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        rechazarOfertaLaboral();

      }
    });
    panelBotones.add(btnRechazar);

    this.btnCerrar = new JButton("Cancelar");
    btnCerrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        limpiarTodosLosDatos();
        dispose();

      }
    });
    panelBotones.add(btnCerrar);

    this.panelDatos = new JPanel();
    getContentPane().add(panelDatos, BorderLayout.CENTER);
    GridBagLayout gblPanelDatos = new GridBagLayout();
    gblPanelDatos.columnWidths = new int[]{
        113, 739, 0};
    gblPanelDatos.rowHeights = new int[]{
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    gblPanelDatos.columnWeights = new double[]{
        0.0, 1.0,
        Double.MIN_VALUE};
    gblPanelDatos.rowWeights = new double[]{
        0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
    panelDatos.setLayout(gblPanelDatos);

    GridBagConstraints gbcLblSeleccion =
          new GridBagConstraints();
    gbcLblSeleccion.insets = new Insets(0, 0, 5, 5);
    gbcLblSeleccion.anchor = GridBagConstraints.EAST;
    gbcLblSeleccion.gridx = 0;
    gbcLblSeleccion.gridy = 1;
    JLabel lblSeleccion = new JLabel(
          "Seleccionar Empresa:");
    panelDatos.add(lblSeleccion, gbcLblSeleccion);

    this.comboBoxSeleccionUsuario = new JComboBox<String>();
    this.comboBoxSeleccionUsuario
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              try {
                cargarDatosUsuarios(evento);
              } catch (UsuarioNoExisteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              }

            }
          });

    GridBagConstraints gbcComboBoxSeleccionUsuario =
          new GridBagConstraints();
    gbcComboBoxSeleccionUsuario.insets = new Insets(0, 0, 5,
          0);
    gbcComboBoxSeleccionUsuario.fill =
          GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionUsuario.gridx = 1;
    gbcComboBoxSeleccionUsuario.gridy = 1;
    panelDatos.add(this.comboBoxSeleccionUsuario,
          gbcComboBoxSeleccionUsuario);

    GridBagConstraints gbcLblOfertas =
          new GridBagConstraints();
    gbcLblOfertas.anchor = GridBagConstraints.EAST;
    gbcLblOfertas.insets = new Insets(0, 0, 5, 5);
    gbcLblOfertas.gridx = 0;
    gbcLblOfertas.gridy = 12;
    JLabel lblOfertas = new JLabel("Ofertas Laborales:");
    panelDatos.add(lblOfertas, gbcLblOfertas);

    this.comboBoxSeleccionOferta = new JComboBox<String>();
    this.comboBoxSeleccionOferta
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              try {
                cargarDatosOferta(evento);
              } catch (OfertaLaboralNoExisteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

              }
            }
          });
    GridBagConstraints gbcComboBoxSeleccionOferta =
          new GridBagConstraints();
    gbcComboBoxSeleccionOferta.insets = new Insets(0, 0, 5,
          0);
    gbcComboBoxSeleccionOferta.fill =
          GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionOferta.gridx = 1;
    gbcComboBoxSeleccionOferta.gridy = 12;
    panelDatos.add(this.comboBoxSeleccionOferta,
          gbcComboBoxSeleccionOferta);

    GridBagConstraints gbcLblHorarioOferta =
          new GridBagConstraints();
    gbcLblHorarioOferta.anchor = GridBagConstraints.EAST;
    gbcLblHorarioOferta.insets = new Insets(0, 0, 5, 5);
    gbcLblHorarioOferta.gridx = 0;
    gbcLblHorarioOferta.gridy = 13;
    JLabel lblHorarioOferta = new JLabel("Horario");
    panelDatos.add(lblHorarioOferta, gbcLblHorarioOferta);

    this.textFieldHorarioOferta = new JTextField();
    this.textFieldHorarioOferta.setEditable(false);
    GridBagConstraints gbcTextFieldNombreOferta =
          new GridBagConstraints();
    gbcTextFieldNombreOferta.insets = new Insets(0, 0, 5,
          0);
    gbcTextFieldNombreOferta.fill =
          GridBagConstraints.HORIZONTAL;
    gbcTextFieldNombreOferta.gridx = 1;
    gbcTextFieldNombreOferta.gridy = 13;
    panelDatos.add(this.textFieldHorarioOferta,
          gbcTextFieldNombreOferta);
    this.textFieldHorarioOferta.setColumns(10);

    GridBagConstraints gbcLblRemuneracion =
          new GridBagConstraints();
    gbcLblRemuneracion.anchor = GridBagConstraints.EAST;
    gbcLblRemuneracion.insets = new Insets(0, 0, 5, 5);
    gbcLblRemuneracion.gridx = 0;
    gbcLblRemuneracion.gridy = 14;
    JLabel lblRemuneracion = new JLabel("Remuneracion");
    panelDatos.add(lblRemuneracion, gbcLblRemuneracion);

    this.textFieldRemuneracion = new JTextField();
    this.textFieldRemuneracion.setEditable(false);
    GridBagConstraints gbcTextFieldRemuneracion =
          new GridBagConstraints();
    gbcTextFieldRemuneracion.insets = new Insets(0, 0, 5,
          0);
    gbcTextFieldRemuneracion.fill =
          GridBagConstraints.HORIZONTAL;
    gbcTextFieldRemuneracion.gridx = 1;
    gbcTextFieldRemuneracion.gridy = 14;
    panelDatos.add(this.textFieldRemuneracion,
          gbcTextFieldRemuneracion);
    this.textFieldRemuneracion.setColumns(10);

    GridBagConstraints gbcLblCiudad =
          new GridBagConstraints();
    gbcLblCiudad.anchor = GridBagConstraints.EAST;
    gbcLblCiudad.insets = new Insets(0, 0, 5, 5);
    gbcLblCiudad.gridx = 0;
    gbcLblCiudad.gridy = 15;
    JLabel lblCiudad = new JLabel("Ciudad");
    panelDatos.add(lblCiudad, gbcLblCiudad);

    this.textFieldCiudad = new JTextField();
    this.textFieldCiudad.setEditable(false);
    GridBagConstraints gbcTextFieldCiudad =
          new GridBagConstraints();
    gbcTextFieldCiudad.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldCiudad.gridx = 1;
    gbcTextFieldCiudad.gridy = 15;
    panelDatos.add(this.textFieldCiudad,
          gbcTextFieldCiudad);
    this.textFieldCiudad.setColumns(10);

    GridBagConstraints gbcLblDepartamento =
          new GridBagConstraints();
    gbcLblDepartamento.anchor = GridBagConstraints.EAST;
    gbcLblDepartamento.insets = new Insets(0, 0, 5, 5);
    gbcLblDepartamento.gridx = 0;
    gbcLblDepartamento.gridy = 16;
    JLabel lblDepartamento = new JLabel("Departamento");
    panelDatos.add(lblDepartamento, gbcLblDepartamento);

    this.textFieldDepartamento = new JTextField();
    this.textFieldDepartamento.setEditable(false);
    GridBagConstraints gbcTextFieldDepartamento =
          new GridBagConstraints();
    gbcTextFieldDepartamento.insets = new Insets(0, 0, 5,
          0);
    gbcTextFieldDepartamento.fill =
          GridBagConstraints.HORIZONTAL;
    gbcTextFieldDepartamento.gridx = 1;
    gbcTextFieldDepartamento.gridy = 16;
    panelDatos.add(this.textFieldDepartamento,
          gbcTextFieldDepartamento);
    this.textFieldDepartamento.setColumns(10);

  }

  /**
   * Metodo cargar empresas .
   */

  public void cargarEmpresas() {
    try {
      List<String> listaEmpresas = this.controladorUsuario
            .listarEmpresas();
      String[] arrayEmpresas;
      arrayEmpresas = listaEmpresas.toArray(new String[0]);
      Arrays.sort(arrayEmpresas);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(
            arrayEmpresas);
      this.comboBoxSeleccionUsuario.setModel(model);

    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  /**
   * Metodo confirmar oferta laboral .
   */

  public void confirmarOfertaLaboral() {
    if (comboBoxSeleccionOferta.getSelectedIndex() == -1) {
      JOptionPane.showMessageDialog(this,
            "Debe seleccionar una oferta laboral",
            "Agregar/Rechazar Oferta Laboral",
            JOptionPane.ERROR_MESSAGE);
    } else {
      String oferta = comboBoxSeleccionOferta
            .getSelectedItem().toString();
      if (!ofertaSeleccionada.equals(oferta)) {
        try {
          DtOfertaLaboral dtOfertaLaboral =
                this.controladorOfertaLaboral
                      .obtenerDtOfertaLaboral(oferta);
          if (!dtOfertaLaboral.getEstadoOferta()
                .equals(EstadoOferta.INGRESADA)) {
            JOptionPane.showMessageDialog(this,
                  "La oferta " + oferta
                        + " no se encuentra en "
                        + "estado ingresada",
                  "Agregar/Rechazar Oferta Laboral",
                  JOptionPane.ERROR_MESSAGE);
          } else {
            LocalDate fechaActual = LocalDate.now();
            this.controladorOfertaLaboral
                  .aceptarRechazarOfertaLaboral(oferta,
                        EstadoOferta.CONFIRMADA,
                        fechaActual);
            JOptionPane.showMessageDialog(this,
                  "La oferta " + oferta
                        + " ha sido confirmada con "
                        + "éxito",
                  "Agregar/Rechazar Oferta Laboral",
                  JOptionPane.INFORMATION_MESSAGE);
            limpiarTodosLosDatos();
          }
        } catch (OfertaLaboralNoExisteException
              | IOException evento) {
          evento.printStackTrace();
        }
      }
    }
  }

  /**
   * Metodo rechazar oferta laboral .
   */

  public void rechazarOfertaLaboral() {
    if (comboBoxSeleccionOferta.getSelectedIndex() == -1) {
      JOptionPane.showMessageDialog(this,
            "Debe seleccionar una oferta laboral",
            "Agregar/Rechazar Oferta Laboral",
            JOptionPane.ERROR_MESSAGE);
    } else {
      String oferta = comboBoxSeleccionOferta
            .getSelectedItem().toString();
      if (ofertaSeleccionada != oferta) {
        try {
          DtOfertaLaboral dtOfertaLaboral =
                this.controladorOfertaLaboral
                      .obtenerDtOfertaLaboral(oferta);
          if (!dtOfertaLaboral.getEstadoOferta()
                .equals(EstadoOferta.INGRESADA)) {
            JOptionPane.showMessageDialog(this,
                  "La oferta " + oferta
                        + " no se encuentra en estado"
                        + " ingresada",
                  "Agregar/Rechazar Oferta Laboral",
                  JOptionPane.ERROR_MESSAGE);
          } else {
            LocalDate fechaActual = LocalDate.now();
            this.controladorOfertaLaboral
                  .aceptarRechazarOfertaLaboral(oferta,
                        EstadoOferta.RECHAZADA,
                        fechaActual);
            JOptionPane.showMessageDialog(this,
                  "La oferta " + oferta
                        + " ha sido rechazada con "
                        + "éxito",
                  "Agregar/Rechazar Oferta Laboral",
                  JOptionPane.INFORMATION_MESSAGE);
            limpiarTodosLosDatos();
          }
        } catch (OfertaLaboralNoExisteException
              | IOException evento) {
          evento.printStackTrace();
        }
      }
    }

  }

  /**
   * Metodo cargar datos usuario .
   */

  public void cargarDatosUsuarios(ActionEvent evento)
        throws UsuarioNoExisteException {
    if (comboBoxSeleccionUsuario.getSelectedIndex() != -1) {
      String nicknameUsuario = comboBoxSeleccionUsuario
            .getSelectedItem().toString();
      List<String> listaOfertas = this.controladorUsuario
            .listaOfertasUsuario(nicknameUsuario);
      String[] arrayOfertas = listaOfertas
            .toArray(new String[0]);
      Arrays.sort(arrayOfertas);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(
            arrayOfertas);
      this.comboBoxSeleccionOferta.setModel(model);
    }
  }

  protected void cargarDatosOferta(ActionEvent evento)
        throws OfertaLaboralNoExisteException {
    String oferta = comboBoxSeleccionOferta
          .getSelectedItem().toString();
    if (ofertaSeleccionada != oferta) {
      DtOfertaLaboral dtOferta;
      try {
        dtOferta = controladorOfertaLaboral
              .obtenerDtOfertaLaboral(oferta);
      } catch (OfertaLaboralNoExisteException
            | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return;
      }
      this.textFieldHorarioOferta
            .setText(dtOferta.getHorarioInicio() + " - "
                  + dtOferta.getHorarioFinal());
      this.textFieldRemuneracion
            .setText(dtOferta.getRemuneracion().toString());
      this.textFieldCiudad.setText(dtOferta.getCiudad());
      this.textFieldDepartamento
            .setText(dtOferta.getDepartamento());
    }
  }

  /**
   * Metodo limpiar datos .
   */

  public void limpiarTodosLosDatos() {

    this.ofertaSeleccionada = "";
    this.textFieldDepartamento.setText("");
    this.textFieldCiudad.setText("");
    this.textFieldHorarioOferta.setText("");
    this.textFieldRemuneracion.setText("");
  }
}
