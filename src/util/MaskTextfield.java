package util;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class MaskTextfield {

	public static void campoNumerico(final TextField textField) {
	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            if (newValue.intValue() > oldValue.intValue()) {
	                char ch = textField.getText().charAt(oldValue.intValue());
	                if (!(ch >= '0' && ch <= '9')) {
	                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
	                }
	            }
	        }
	    });
	}
	
	public static void campoNumericoComPonto(final TextField textField) {
	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            if (newValue.intValue() > oldValue.intValue()) {
	                char ch = textField.getText().charAt(oldValue.intValue());
	                if (!(ch >= '0' && ch <= '9' || ch == '.')) {
	                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
	                }
	            }
	        }
	    });
	}
	
	public static void tamanhoMaximo(final TextField textField, final Integer tamanho) {
	    textField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
	            if (newValue.length() > tamanho)textField.setText(oldValue);
	        }
	    });
	}
}
