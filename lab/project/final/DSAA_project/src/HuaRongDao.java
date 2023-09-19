import javax.swing.*;
import java.util.Random;
import java.util.Stack;

public class HuaRongDao extends JFrame {

    int[][] datas = new int[][]{{1,2,3}, {4,5,6}, {7,8,0},{9,10,11}};
    JButton go;
    JButton reset;
    JPanel jPanel;
    int row;//行数
    int col;//列数
    Stack<int[][]> input;//获取每一步的棋盘

    public void setDatas(int[][] datas) {
        this.datas = datas;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    //构造方法
    public HuaRongDao(Stack<int[][]> input){
        this.input = input;
        //判断input是否为空；为空则无解
        if(input.isEmpty()){
            JOptionPane.showMessageDialog(this, "No solution");
            System.exit(0);
        }
        //传入初始棋盘
        setDatas(this.input.pop());
        //窗体初始化设置
        initFrame();
        //窗体上的组件设置显示
        paintView();
        //添加按钮事件
        addButtonEvent();
        //设置窗体可见
        this.setVisible(true);
        //设置棋盘大小
        setRow(datas.length);
        setCol(datas[0].length);
    }

    //显示窗体方法
    public void initFrame() {
        this.setTitle("数字华容道");
        this.setSize(960, 765);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    //胜利弹出窗口
    public void View(){
        JOptionPane.showMessageDialog(this, "游戏胜利！！！");
    }


    //组件设置
    public void paintView() {

        //标题设置
        JLabel title = new JLabel("Numeric Ktloski");
        title.setBounds(354, 27, 232, 57);
        this.add(title);

        //游戏面板设置
        jPanel = new JPanel();
        jPanel.setBounds(150, 114, 480, 480);
        jPanel.setLayout(null);
        //把图片放进去
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                JLabel picture = new JLabel(new ImageIcon("Image\\" + datas[i][j] + ".png"));
                picture.setBounds(90 * j, 90 * i, 90, 90);
                jPanel.add(picture);
            }
        }
        this.add(jPanel);


        //按钮
        go = new JButton("go");
        go.setBounds(626, 344, 108, 45);
        this.add(go);
        reset = new JButton("reset");
        reset.setBounds(786, 344, 108, 45);
        this.add(reset);

        //背景设置
        JLabel background = new JLabel();
        background.setBounds(0, 0, 960, 530);
        this.add(background);
    }

    //编写方法生成有解二维数组
    public void upset() {
        int count = 1;
        Random rd = new Random();
        int n = 3;
        int m = 3;
        int[][] newData = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newData[i][j] = count;
                count++;
            }
        }
        newData[n - 1][m - 1] = 0;
        int ci = HuaRongDao.inverseNum(newData);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = rd.nextInt(n);
                int y = rd.nextInt(m);
                int temp = newData[i][j];
                newData[i][j] = newData[x][y];
                newData[x][y] = temp;
            }
        }

        while (HuaRongDao.inverseNum(newData) % 2 != ci % 2){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int x = rd.nextInt(n);
                    int y = rd.nextInt(m);
                    int temp = newData[i][j];
                    newData[i][j] = newData[x][y];
                    newData[x][y] = temp;
                }
            }
        }
        datas = newData;
        Node data = new Node(datas, null, 0, null);
        this.input = Node.answer(data);
        input.pop();
        repaint();
    }

    //编写方法给按钮添加事件
    public void addButtonEvent() {
       reset.addActionListener(e -> {
            upset();
        });
        go.addActionListener(e -> {
            //TODO:算法部分
            setDatas(input.pop());
            repaint();
            if(input.isEmpty()){
                View();
            }
        });
    }

    //编写重绘方法
    public void repaint() {
        //移除面板上所有组件
        jPanel.removeAll();

        //重新绘制画板内容
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                JLabel lb = new JLabel(new ImageIcon("Image\\" + datas[i][j] + ".png"));
                lb.setBounds(90 * j, 90 * i, 90, 90);
                jPanel.add(lb);
            }
        }
        this.add(jPanel);
        //添加到画板上
        jPanel.repaint();
    }

    public static int inverseNum(int[][] input){
        int output = 0;
        r:
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if(input[i][j] == 0){
                    output = output + i + j;
                    break r;
                }
            }
        }
        int[] total = new int[input.length * input[0].length];
        for (int i = 0; i < input.length * input[0].length; i++) {
            total[i] = input[i / input[0].length][i % input[0].length];
        }

        for (int i = 0; i < total.length; i++) {
            for (int j = 0; j < i; j++) {
                if (total[i] < total[j]){
                    output++;
                }
            }
        }
        return output;
    }
}