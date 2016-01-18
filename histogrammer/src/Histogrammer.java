
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

public class Histogrammer {

	public static void main(String[] args) {



		JFrame  jf = new JFrame("Kalman filter");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 222, 200, 216);
		jf.getContentPane().add(scrollPane);

		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		//jf.pack();
		OutputStream out = new OutputStream() {
			@Override
			public void write(final int b) throws IOException {
				textArea.append((String.valueOf((char) b)));
			}

			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				textArea.append((new String(b, off, len)));
			}

			@Override
			public void write(byte[] b) throws IOException {
				textArea.append(new String(b, 0, b.length));
			}
		};
		System.setOut(new PrintStream(out, true));
		System.setErr(new PrintStream(out, true));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		jf.setSize(1200, 600);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(10, 11, 200, 200);
		jf.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);

		final JToggleButton btnGR = new JToggleButton("Gyro Roll");

		btnGR.setSelected(false);
		btnGR.setBounds(0, 11, 121, 23);
		buttonPanel.add(btnGR);

		JButton btnReReadData = new JButton("Re read data");

		btnReReadData.setBounds(0, 166, 121, 23);
		buttonPanel.add(btnReReadData);


		JPanel panel = new JPanel();
		PlotOrientation orientation = PlotOrientation.VERTICAL;
		panel.setBounds(216, 11, 800, 500);
		
		JFreeChart chart = ChartFactory.createHistogram( "Histogram", "channel", "counts",Histogrammer.createDataset(null), orientation, false, false, false);
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		panel.validate();
		panel.setVisible(true);
		jf.getContentPane().add(panel);


		jf.setVisible(true);
		jf.validate();
		System.err.println("Test");
		System.out.println("done");
	}
	/**
	 * This creates a dataset for the chart from the input data
	 * @param floatArray The array to input, uses the format from the ExcelReader.read() method
	 * @return A dataset hat can be used to plot a graph
	 */
	private static HistogramDataset createDataset(double[] floatArray) {
		HistogramDataset dataset = new HistogramDataset();
	    dataset.setType(HistogramType.RELATIVE_FREQUENCY);
	    dataset.addSeries("Histogram",floatArray,1);
		/*
		final XYSeries gRoll = new XYSeries("Gyro Roll");
		final XYSeries gPitch = new XYSeries("Gyro Pitch");
		final XYSeries gYaw = new XYSeries("Gyro Yaw");
		final XYSeries aX = new XYSeries("Accel X");
		final XYSeries aY = new XYSeries("Accel Y");
		final XYSeries aZ = new XYSeries("Accel Z");

		int i=0;
		while(i<floatArray.length){
			gRoll.add(i,floatArray[i][0]);
			gPitch.add(i,floatArray[i][1]);
			gYaw.add(i,floatArray[i][2]);
			aX.add(i,floatArray[i][3]);
			aY.add(i,floatArray[i][4]);
			aZ.add(i,floatArray[i][5]);
			i++;
		}


		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(gRoll);
		dataset.addSeries(gPitch);
		dataset.addSeries(gYaw);
		dataset.addSeries(aX);
		dataset.addSeries(aY);
		dataset.addSeries(aZ);

*/
		return dataset;


	}

}
