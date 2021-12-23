package edu.sgu.gui.menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import edu.sgu.gui.formHienThi.MainGUI;


public class MenuItem extends JPanel {

	private final ArrayList<MenuItem> subMenu = new ArrayList<>();
	private ActionListener act;
	private boolean showing = false;
	private JLabel lblName;
	private JLabel lblIcon;
	private JSeparator separator;
	private JLabel lblPlus;
	private ImageIcon image_plus;

	public void setShowing(boolean showing) {
		this.showing = showing;
	}

	public ArrayList<MenuItem> getSubMenu() {
		return subMenu;
	}

	public MenuItem(ImageIcon icon, String menuName, ActionListener act, ImageIcon plus,MenuItem... subMenu) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (act != null) {
					act.actionPerformed(null);
				}
			}
		});
		MenuItem(plus);
		lblIcon.setIcon(icon);
		lblName.setText(menuName);
		if (act != null) {
			this.act = act;
		}
		this.setSize(new Dimension(Integer.MAX_VALUE, 70));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
		this.setMinimumSize(new Dimension(Integer.MAX_VALUE, 70));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(separator)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE).addGap(12)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)));

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(13).addComponent(lblName)))
						.addGap(9).addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)));
		setLayout(groupLayout);

		for (int i = 0; i < subMenu.length; i++) {
			this.subMenu.add(subMenu[i]);
			subMenu[i].setVisible(false);
		}
	}

	private void MenuItem(ImageIcon plus) {
		setSize(330, 70);
		lblIcon = new JLabel("");
		lblIcon.setBounds(12, 10, 56, 50);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);

		lblName = new JLabel("");
		lblName.setBounds(88, 23, 190, 24);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		separator = new JSeparator();
		setBackground(new Color(47, 79, 79));

		if (plus != null) {
			lblPlus = new JLabel("");
			lblPlus.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					formMousePressed(e);
				}
			});
			lblPlus.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlus.setBounds(265, 17, 40, 40);
			image_plus = new ImageIcon(MainGUI.class.getResource("/images/chevron-down.png"));
			image_plus.setImage(image_plus.getImage().getScaledInstance(lblPlus.getWidth(), lblPlus.getHeight(), Image.SCALE_SMOOTH));
			lblPlus.setIcon(image_plus);
			lblPlus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			add(lblPlus);
		}
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(new Color(112, 128, 144));
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(new Color(60, 179, 113));				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(47, 79, 79));				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(112, 128, 144));								
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	private void formMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMousePressed
		if (showing) {
			lblName.setForeground(Color.WHITE);

			image_plus = new ImageIcon(MainGUI.class.getResource("/images/chevron-down.png"));
			image_plus.setImage(image_plus.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
			lblPlus.setIcon(image_plus);
			
			ImageIcon image_list = new ImageIcon(MainGUI.class.getResource("/images/list.png"));
			image_list.setImage(image_list.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			lblIcon.setIcon(image_list);
			
			hideMenu();
		} else {
			lblName.setForeground(Color.LIGHT_GRAY);

			image_plus = new ImageIcon(MainGUI.class.getResource("/images/chevron-up.png"));
			image_plus.setImage(image_plus.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
			lblPlus.setIcon(image_plus);
			
			ImageIcon image_unList = new ImageIcon(MainGUI.class.getResource("/images/un-list.png"));
			image_unList.setImage(image_unList.getImage().getScaledInstance(56, 50, Image.SCALE_SMOOTH));
			lblIcon.setIcon(image_unList);
			
			showMenu();
		}

	}// GEN-LAST:event_formMousePressed

	private void showMenu() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < subMenu.size(); i++) {
					sleep();
					subMenu.get(i).setVisible(true);
				}
				showing = true;
				getParent().repaint();
				getParent().revalidate();
			}
		}).start();
	}

	private void hideMenu() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = subMenu.size() - 1; i >= 0; i--) {
					sleep();
					subMenu.get(i).setVisible(false);
					subMenu.get(i).hideMenu();
				}
				getParent().repaint();
				getParent().revalidate();
				showing = false;
			}
		}).start();
	}

	private void sleep() {
		try {
			Thread.sleep(20);
		} catch (Exception e) {
		}
	}
}
