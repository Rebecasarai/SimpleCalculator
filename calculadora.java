package entrega;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class calculadora extends JFrame implements ActionListener{
	private JPanel principal,p1, teclas;
	private JTextField pantalla;
	//Para saber si habra nueva operacion
	boolean nuevaOperacion;
	//Para guardar el resultado de la operacion anterior o el número tecleado 
	double resultado;
	// para guardar la operacion a realizar 
	String operacion;

	
	//Donde la magia ocurre
	public calculadora(){
		this.setTitle("Calculadora");
		principal = new JPanel();
		principal.setLayout(new GridLayout(2,1));
		//Header
		p1 = new JPanel();
		p1.setLayout(new GridLayout(2,1));
		JLabel etiqueta1 = new JLabel("La Calculadora de Rebeca");
		etiqueta1.setHorizontalAlignment(JLabel.CENTER);
		etiqueta1.setFont(new Font("Courier", Font.BOLD, 16));
		p1.add(etiqueta1);
		pantalla = new JTextField("0", 30);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.BOLD, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.WHITE);
		p1.add("North", pantalla);
		
		//Buttons
		teclas = new JPanel();
		teclas.setLayout(new GridLayout(3,3));
		
		
		/*for (int i = 0; i < 10; i++) {
			String letra = ""+i;
			botones[i] = new JButton(letra);
			numeros.add(botones[i]);
			botones[i].addActionListener(this);
		}*/
		
		for (int i = 9; i >= 0; i--) {
			
			nuevoBotonNum("" + i);
		}
		
		nuevoBotonNum(".");
		

		nuevoBotonOperacion("+");
		nuevoBotonOperacion("-");
		nuevoBotonOperacion("*");
		nuevoBotonOperacion("/");
		nuevoBotonOperacion("=");
		nuevoBotonOperacion("CE");
		nuevoBotonOperacion("Raiz");
		
		principal.add(p1);
		principal.add(teclas);
		
		
		//Basicos
		this.add(principal);
		this.setSize(400,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
	}
	
	
	private void nuevoBotonNum(String digito) {
		JButton boton = new JButton();
		boton.setText(digito);
		boton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton boton = (JButton) evt.getSource();
				numeroPulsado(boton.getText());
			}
		});

		teclas.add(boton);
	}
	private JButton nuevoBotonOperacion(String operacion) {
		JButton boton = new JButton(operacion);
		boton.setForeground(Color.RED);

		boton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton boton = (JButton) evt.getSource();
				operacionPulsado(boton.getText());
			}
		});

		teclas.add(boton);
		return boton;
	}

	
	private void numeroPulsado(String digito){
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}
	
	private void operacionPulsado(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("CE")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
	}
	private void calcularResultado() {
		if (operacion.equals("+")) {
			resultado += new Double(pantalla.getText());
		} else if (operacion.equals("-")) {
			resultado -= new Double(pantalla.getText());
		} else if (operacion.equals("/")) {
			resultado /= new Double(pantalla.getText());
		} else if (operacion.equals("*")) {
			resultado *= new Double(pantalla.getText());
		}else if (operacion.equals("√")){
			resultado = Math.sqrt(resultado);
		}

		pantalla.setText("" + resultado);
		operacion = "";
	}

	
	public static void main(String[] args) {
		new calculadora();
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		
		
	}


	
}
