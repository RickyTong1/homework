using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace WindowsFormsApplication4
{
    public partial class Form1 : Form
    {
        int flag = 0;
        public Form1()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            if (flag == 3)
            {
                MessageBox.Show("密码错误次数过多");
            }
            else if (textBox1.Text == "5043" && textBox2.Text == "5043")
            {
                Form2 form2 = new Form2();
                form2.Show();
            }
            else
            {
                MessageBox.Show("密码错误");
                flag++;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
