package view;

import control.Datas;
import entity.Student;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ManagementScreen {
    JFrame managementScreen;
    Container container = new Container();
    JPanel blank, blankMenu, menu, newStudent, eastJPanel, finStudents, uptStudent, showStudent;

    public ManagementScreen() throws InterruptedException {

        finStudents = finStudentsJPanel();
        uptStudent = uptStudentJPanel();
        showStudent = showJPanel();

        managementScreen = new JFrame("学生管理系统");
        container = managementScreen.getContentPane();
        blank = blankFunctionJPanel();
        eastJPanel = blank;
        blankMenu = blankMenuJPanel();
        menu = menuJPanel();
        newStudent = newStudentJPanel();
        managementScreen.setBounds(300, 300, 800, 400);
        managementScreen.setLayout(new BorderLayout());
        container.add(blankMenu, BorderLayout.WEST);
        container.add(blank, BorderLayout.CENTER);

        managementScreen.setVisible(true);
    }

    public JPanel menuJPanel() {
        JPanel menu = new JPanel();
        JList<JButton> jButtonJList = new JList<>();
        menu.setLayout(new GridLayout(5, 1));
        JButton jButton1 = new JButton("新增学生");
        JButton jButton2 = new JButton("修改学生");
        JButton jButton4 = new JButton("查询学生");
        JButton jButton5 = new JButton("信息统计");

        menu.add(jButton1);
        menu.add(jButton2);
        menu.add(jButton4);
        menu.add(jButton5);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnJPanel(newStudent);
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnJPanel(uptStudent);
            }
        });
        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnJPanel(finStudents);
            }
        });
        jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnJPanel(showStudent);
            }
        });

        return menu;
    }

    public JPanel blankFunctionJPanel() {
        JPanel blankJPanel = new JPanel();
        JLabel tips = new JLabel("请选择左边菜单栏");
        blankJPanel.add(tips);
        return blankJPanel;
    }

    public JPanel blankMenuJPanel() {
        JPanel blankMenu = new JPanel();
        blankMenu.setLayout(new GridLayout());
        JButton menuButton = new JButton("菜单");
        blankMenu.add(menuButton);

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    container.remove(blankMenu);
                    container.add(menu, BorderLayout.WEST);
                    managementScreen.setSize(managementScreen.getWidth() - 1, managementScreen.getHeight() - 1);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    container.remove(menu);
                    container.add(blankMenu, BorderLayout.WEST);
                    managementScreen.setSize(managementScreen.getWidth() - 1, managementScreen.getHeight() - 1);
                }).start();
            }
        });

        return blankMenu;
    }

    public JPanel newStudentJPanel() {
        JPanel newStudent = new JPanel();
        newStudent.setLayout(new GridLayout(6, 3));
        JLabel nameLabel = new JLabel("姓名：", SwingConstants.RIGHT);
        JTextField nameText = new JTextField();
        JLabel nameTip = new JLabel();
        JLabel sexLabel = new JLabel("性别：", SwingConstants.RIGHT);
        JComboBox sexText = new JComboBox();
        sexText.addItem("男");
        sexText.addItem("女");
        JLabel majorLabel = new JLabel("专业：", SwingConstants.RIGHT);
        JTextField majorText = new JTextField();
        JLabel majorTip = new JLabel();
        JLabel enrollmentDateLabel = new JLabel("入学时间：", SwingConstants.RIGHT);
        JTextField enrollmentDateText = new JTextField(new SimpleDateFormat("yyyy").format(new Date()) + "-1-1");
        JLabel enrollmentDateTip = new JLabel();
        JLabel scoreLabel = new JLabel("成绩：", SwingConstants.RIGHT);
        JTextField scoreText = new JTextField();
        newStudent.add(nameLabel); newStudent.add(nameText);
        newStudent.add(nameTip);
        newStudent.add(sexLabel); newStudent.add(sexText);
        newStudent.add(new JLabel());
        newStudent.add(majorLabel); newStudent.add(majorText);
        newStudent.add(majorTip);
        newStudent.add(enrollmentDateLabel); newStudent.add(enrollmentDateText);
        newStudent.add(enrollmentDateTip);
        newStudent.add(scoreLabel); newStudent.add(scoreText);
        JLabel scoreTip = new JLabel();
        newStudent.add(scoreTip);
        newStudent.add(new JLabel());
        JButton newAStudent = new JButton("确定");
        JLabel tips = new JLabel("");
        newStudent.add(tips);
        newStudent.add(newAStudent);

        newAStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameText.getText().equals("")) {
                    JLabelThread jLabelThread = new JLabelThread(nameTip, "姓名不能为空");
                    return;
                }
                if (majorText.getText().equals("")) {
                    JLabelThread jLabelThread = new JLabelThread(majorTip, "专业不能为空");
                    return;
                }
                if (enrollmentDateText.getText().equals("")) {
                    JLabelThread jLabelThread = new JLabelThread(enrollmentDateTip, "入学时间不能为空");
                    return;
                }
                try {
                    if (Double.parseDouble(scoreText.getText()) < 0 || Double.parseDouble(scoreText.getText()) > 100) {
                        JLabelThread jLabelThread = new JLabelThread(scoreTip, "成绩无效，应为0-100范围");
                        newStudent.repaint();
                    }
                } catch(Exception exception){
                    JLabelThread jLabelThread = new JLabelThread(scoreTip, "成绩格式错误");
                    newStudent.repaint();
                }

                String regex = "(1|2)[0-9]{3}-(0[1-9]|1[0-2]|[1-9])-(0[1-9]|[1-2][0-9]|[1-9]|30)";
                if (!enrollmentDateText.getText().matches(regex)) {
                    JLabelThread jLabelThread = new JLabelThread(enrollmentDateTip, "日期格式请按”年-月-日“");
                    enrollmentDateText.setText("");
                    return;
                }

                Student student = null;
                student = new Student(null, nameText.getText(), (String)sexText.getItemAt(sexText.getSelectedIndex()), majorText.getText(), enrollmentDateText.getText(), scoreText.getText());
                Datas datas = new Datas();
                try {
                    if (!datas.setStudent(student)) {
                         JLabelThread jLabelThread = new JLabelThread(majorTip, "无该专业");
                    } else {

                        JLabelThread jLabelThread = new JLabelThread(tips, "新增学生成功");
                        tips.setForeground(Color.green);
                        nameText.setText("");
                        majorText.setText("");
                        enrollmentDateText.setText("");
                        scoreText.setText("");
                        newStudent.repaint();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        return newStudent;
    }

    // 无法判断专业，日期也有点问题
    public JPanel uptStudentJPanel() throws InterruptedException {
        JPanel uptStudent = new JPanel();
        uptStudent.setLayout(new BorderLayout());

        JPanel finStudent = finStudentJPanel();
        final JPanel[] centerJPanel = {new JPanel()};
        JPanel optionJPanel = new JPanel();
        optionJPanel.setLayout(new FlowLayout(0));
        optionJPanel.setBorder(BorderFactory.createLineBorder(Color.white, 10));
        JPanel uptJPanel = newStudentJPanel();
        uptJPanel.setLayout(new GridLayout(7, 3));
        uptJPanel.remove(15);
        uptJPanel.remove(15);
        uptJPanel.remove(15);
        JButton delJButton = new JButton("删除");
        JButton uptJButton = new JButton("修改");
        JButton returnJButton = new JButton("返回");
        uptJPanel.add(delJButton);
        uptJPanel.add(uptJButton);
        uptJPanel.add(returnJButton);
        uptJPanel.add(new JLabel("学号：", SwingConstants.RIGHT), 0);
        uptJPanel.add(new JLabel(), 1);
        uptJPanel.add(new JLabel(), 2);



        uptStudent.add(finStudent, BorderLayout.NORTH);
        uptStudent.add(centerJPanel[0], BorderLayout.CENTER);

        JButton fin = (JButton) finStudent.getComponent(8);
        JTextField sid = (JTextField) finStudent.getComponent(1);
        JTextField sname = (JTextField) finStudent.getComponent(3);
        JTextField smajor = (JTextField) finStudent.getComponent(5);
        JTextField syear = (JTextField) finStudent.getComponent(7);


        ActionListener finAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uptStudent.remove(centerJPanel[0]);
                Datas datas = new Datas();
                Student student = new Student(sid.getText(), sname.getText(), smajor.getText(), syear.getText());
                ArrayList<Student> students;
                try {
                    students = datas.findStudent(student);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                optionJPanel.removeAll();
                for (Student student1: students) {
                    JButton jLabel = new JButton("" + student1);
                    jLabel.setFont(new Font("宋体",Font.BOLD, 15));
                    jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    jLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            uptStudent.remove(centerJPanel[0]);
                            uptStudent.add(uptJPanel, BorderLayout.CENTER);
                            centerJPanel[0] = uptJPanel;
                            flush();
                            JLabel id = (JLabel) uptJPanel.getComponent(1);
                            id.setText(student1.getId());
                            JTextField name = (JTextField) uptJPanel.getComponent(4);
                            name.setText(student1.getName());
                            JComboBox sex = (JComboBox) uptJPanel.getComponent(7);
                            sex.setSelectedItem(student1.getSex());
                            JTextField major = (JTextField) uptJPanel.getComponent(10);
                            major.setText(student1.getMajor());
                            JTextField enrollmentdate = (JTextField) uptJPanel.getComponent(13);
                            enrollmentdate.setText(student1.getEnrollmentDate());
                            JTextField score = (JTextField) uptJPanel.getComponent(16);
                            score.setText(student1.getScore());
                        }
                    });
                    optionJPanel.add(jLabel);
                }
                uptStudent.add(optionJPanel, BorderLayout.CENTER);
                centerJPanel[0] = optionJPanel;
                flush();
            }
        };

        returnJButton.addActionListener(finAction);
        delJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel id = (JLabel) uptJPanel.getComponent(1);
                Student student2 = new Student(id.getText());
                Datas datas = new Datas();
                try {
                    datas.delStudent(student2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                uptStudent.remove(centerJPanel[0]);
                Student student = new Student(sid.getText(), sname.getText(), smajor.getText(), syear.getText());
                ArrayList<Student> students;
                try {
                    students = datas.findStudent(student);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                optionJPanel.removeAll();
                for (Student student1: students) {
                    JButton jLabel = new JButton("" + student1);
                    jLabel.setFont(new Font("宋体",Font.BOLD, 15));
                    jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    jLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            uptStudent.remove(centerJPanel[0]);
                            uptStudent.add(uptJPanel, BorderLayout.CENTER);
                            centerJPanel[0] = uptJPanel;
                            flush();
                            JLabel id = (JLabel) uptJPanel.getComponent(1);
                            id.setText(student1.getId());
                            JTextField name = (JTextField) uptJPanel.getComponent(4);
                            name.setText(student1.getName());
                            JComboBox sex = (JComboBox) uptJPanel.getComponent(7);
                            sex.setSelectedItem(student1.getSex());
                            JTextField major = (JTextField) uptJPanel.getComponent(10);
                            major.setText(student1.getMajor());
                            JTextField enrollmentdate = (JTextField) uptJPanel.getComponent(13);
                            enrollmentdate.setText(student1.getEnrollmentDate());
                            JTextField score = (JTextField) uptJPanel.getComponent(16);
                            score.setText(student1.getScore());
                        }
                    });
                    optionJPanel.add(jLabel);
                }
                uptStudent.add(optionJPanel, BorderLayout.CENTER);
                centerJPanel[0] = optionJPanel;
                flush();
            }


        });
        uptJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel idText = (JLabel) uptJPanel.getComponent(1);
                JTextField nameText = (JTextField) uptJPanel.getComponent(4);
                JComboBox sexText = (JComboBox) uptJPanel.getComponent(7);
                JTextField majorText = (JTextField) uptJPanel.getComponent(10);
                JTextField enrollmentDateText = (JTextField) uptJPanel.getComponent(13);
                JTextField scoreText = (JTextField) uptJPanel.getComponent(16);

                JLabel nameTip = (JLabel) uptJPanel.getComponent(5);
                JLabel majorTip = (JLabel) uptJPanel.getComponent(11);
                JLabel enrollmentDateTip = (JLabel) uptJPanel.getComponent(14);
                JLabel scoreTip = (JLabel) uptJPanel.getComponent(17);

                if (nameText.getText().equals("")) {
                    JLabelThread jLabelThread = new JLabelThread(nameTip, "姓名不能为空");
                    return;
                }
                if (majorText.getText().equals("")) {
                    JLabelThread jLabelThread = new JLabelThread(majorTip, "专业不能为空");
                    return;
                }
                if (enrollmentDateText.getText().equals("")) {
                    JLabelThread jLabelThread = new JLabelThread(enrollmentDateTip, "入学时间不能为空");
                    return;
                }
                try {
                    if (Double.parseDouble(scoreText.getText()) < 0 || Double.parseDouble(scoreText.getText()) > 100) {
                        JLabelThread jLabelThread = new JLabelThread(scoreTip, "成绩无效，应为0-100范围");
                        newStudent.repaint();
                        return;
                    }
                } catch(Exception exception){
                    JLabelThread jLabelThread = new JLabelThread(scoreTip, "成绩格式错误");
                    newStudent.repaint();
                    return;
                }

                String regex = "(1|2)[0-9]{3}-(0[1-9]|1[0-2]|[1-9])-(0[1-9]|[1-2][0-9]|[1-9]|30)";
                if (!enrollmentDateText.getText().matches(regex)) {
                    JLabelThread jLabelThread = new JLabelThread(enrollmentDateTip, "日期格式请按”年-月-日“");
                    enrollmentDateText.setText("");
                    return;
                }
                Student student2 = null;
                student2 = new Student(idText.getText(), nameText.getText(), (String)sexText.getItemAt(sexText.getSelectedIndex()), majorText.getText(), enrollmentDateText.getText(), scoreText.getText());
                Datas datas = new Datas();
                try {
                    if (!datas.uptStudent(student2)) {
                        JLabelThread jLabelThread = new JLabelThread(majorTip, "专业不存在");
                        return;
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                uptStudent.remove(centerJPanel[0]);
                Student student = new Student(sid.getText(), sname.getText(), smajor.getText(), syear.getText());
                ArrayList<Student> students;
                try {
                    students = datas.findStudent(student);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                optionJPanel.removeAll();
                for (Student student1: students) {
                    JButton jLabel = new JButton("" + student1);
                    jLabel.setFont(new Font("宋体",Font.BOLD, 15));
                    jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    jLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            uptStudent.remove(centerJPanel[0]);
                            uptStudent.add(uptJPanel, BorderLayout.CENTER);
                            centerJPanel[0] = uptJPanel;
                            flush();
                            JLabel id = (JLabel) uptJPanel.getComponent(1);
                            id.setText(student1.getId());
                            JTextField name = (JTextField) uptJPanel.getComponent(4);
                            name.setText(student1.getName());
                            JComboBox sex = (JComboBox) uptJPanel.getComponent(7);
                            sex.setSelectedItem(student1.getSex());
                            JTextField major = (JTextField) uptJPanel.getComponent(10);
                            major.setText(student1.getMajor());
                            JTextField enrollmentdate = (JTextField) uptJPanel.getComponent(13);
                            enrollmentdate.setText(student1.getEnrollmentDate());
                            JTextField score = (JTextField) uptJPanel.getComponent(16);
                            score.setText(student1.getScore());
                        }
                    });
                    optionJPanel.add(jLabel);
                }
                uptStudent.add(optionJPanel, BorderLayout.CENTER);
                centerJPanel[0] = optionJPanel;
                flush();
            }
        });
        fin.addActionListener(finAction);



        return uptStudent;
    }

    public JPanel showJPanel() {
        JPanel show = new JPanel();
        show.setLayout(new BorderLayout());

        JPanel showMenu = new JPanel(new GridLayout());
        JButton performance = new JButton("成绩统计");
        JButton enrollDates = new JButton("入学时间排序");
        showMenu.add(performance);
        showMenu.add(enrollDates);

        JPanel board = new JPanel(new GridLayout());

        show.add(showMenu, BorderLayout.NORTH);
        show.add(board, BorderLayout.CENTER);

        performance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.removeAll();
                Datas datas = new Datas();
                Student student = new Student("", "", "", "");
                ArrayList<Student> students;
                try {
                    students = datas.findStudent(student);
                    Collections.sort(students, new Comparator<Student>() {
                        @Override
                        public int compare(Student o1, Student o2) {
                            float f1 = Float.parseFloat(o1.getScore()), f2 = Float.parseFloat(o2.getScore());
                            return f1 > f2 ? 1 : -1;
                        }
                    });
                    for (Student student1: students) {
                        JLabel jLabel = new JLabel("" + student1);
                        jLabel.setFont(new Font("宋体",Font.BOLD, 15));
                        jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        board.add(jLabel);
                    }
                    board.setLayout(new GridLayout(students.size(),1));
                    flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        enrollDates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.removeAll();
                Datas datas = new Datas();
                Student student = new Student("", "", "", "");
                ArrayList<Student> students;

                try {
                    students = datas.findStudent(student);
                    Collections.sort(students, new Comparator<Student>() {
                        @Override
                        public int compare(Student o1, Student o2) {
                            int y1 = Integer.parseInt(o1.getEnrollmentDate().substring(0, 4));
                            int y2 = Integer.parseInt(o2.getEnrollmentDate().substring(0, 4));
                            return y2 - y1;
                        }
                    });
                    for (Student student1: students) {
                        JLabel jLabel = new JLabel("" + student1);
                        jLabel.setFont(new Font("宋体",Font.BOLD, 15));
                        jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        board.add(jLabel);
                    }
                    board.setLayout(new GridLayout(students.size(),1));
                    flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return show;
    }


    public void flush() {
        managementScreen.setSize(managementScreen.getWidth() - 1, managementScreen.getHeight() - 1);
        managementScreen.setSize(managementScreen.getWidth() + 1, managementScreen.getHeight() + 1);
        managementScreen.repaint();
    }

    public JPanel finStudentJPanel() {

        JPanel finStudent = new JPanel();
        finStudent.setLayout(new GridLayout(1, 9));

        JLabel id = new JLabel("学号：");
        id.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField sid = new JTextField();
        JLabel name = new JLabel("姓名：");
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField sname = new JTextField();
        JLabel major = new JLabel("专业");
        major.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField smajor = new JTextField();
        JLabel year = new JLabel("入学年份");
        year.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField syear = new JTextField();
        JButton fin = new JButton("查询");
        finStudent.add(id); finStudent.add(sid);
        finStudent.add(name); finStudent.add(sname);
        finStudent.add(major); finStudent.add(smajor);
        finStudent.add(year); finStudent.add(syear);
        finStudent.add(fin);



        return finStudent;
    }

    public JPanel finStudentsJPanel() {


        JPanel finStudents = new JPanel();
        finStudents.setLayout(new BorderLayout());


        JPanel showStudent = new JPanel();
        showStudent.setLayout(new FlowLayout(0));
        showStudent.setBorder(BorderFactory.createLineBorder(Color.white, 10));


        JPanel finStudent = finStudentJPanel();
        JButton fin =  (JButton) finStudent.getComponent(8);
        JTextField sid = (JTextField) finStudent.getComponent(1);
        JTextField sname = (JTextField) finStudent.getComponent(3);
        JTextField smajor = (JTextField) finStudent.getComponent(5);
        JTextField syear = (JTextField) finStudent.getComponent(7);

        finStudents.add(finStudent, BorderLayout.NORTH);
        finStudents.add(showStudent, BorderLayout.CENTER);


        fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datas datas = new Datas();
                Student student = new Student(sid.getText(), sname.getText(), smajor.getText(), syear.getText());
                ArrayList<Student> students;
                try {
                    students = datas.findStudent(student);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                showStudent.removeAll();
                for (Student student1: students) {
                    JLabel jLabel = new JLabel("" + student1);
                    jLabel.setFont(new Font("宋体",Font.BOLD, 15));
                    jLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    showStudent.add(jLabel);
                }
                managementScreen.setSize(managementScreen.getWidth() - 1, managementScreen.getHeight() - 1);
                managementScreen.setSize(managementScreen.getWidth() + 1, managementScreen.getHeight() + 1);
            }
        });

        return finStudents;
    }

    public void turnJPanel(JPanel jPanel) {
        if (eastJPanel == jPanel) {
            return;
        }
        container.remove(eastJPanel);
        container.add(jPanel);
        eastJPanel = jPanel;
        managementScreen.setSize(managementScreen.getWidth() - 1, managementScreen.getHeight() - 1);
        managementScreen.setSize(managementScreen.getWidth() + 1, managementScreen.getHeight() + 1);
    }
}
