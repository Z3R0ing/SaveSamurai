namespace SaveSamurai
{
    partial class Launcher
    {
        /// <summary>
        /// Обязательная переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Требуемый метод для поддержки конструктора — не изменяйте 
        /// содержимое этого метода с помощью редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            this.pane = new System.Windows.Forms.Panel();
            this.butNew = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.pane.SuspendLayout();
            this.SuspendLayout();
            // 
            // pane
            // 
            this.pane.Controls.Add(this.button3);
            this.pane.Controls.Add(this.button2);
            this.pane.Controls.Add(this.button1);
            this.pane.Controls.Add(this.butNew);
            this.pane.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pane.Location = new System.Drawing.Point(0, 0);
            this.pane.Name = "pane";
            this.pane.Size = new System.Drawing.Size(784, 561);
            this.pane.TabIndex = 0;
            // 
            // butNew
            // 
            this.butNew.Font = new System.Drawing.Font("Arial", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(204)));
            this.butNew.Location = new System.Drawing.Point(320, 205);
            this.butNew.Name = "butNew";
            this.butNew.Size = new System.Drawing.Size(160, 40);
            this.butNew.TabIndex = 0;
            this.butNew.Text = "Новая игра";
            this.butNew.UseVisualStyleBackColor = true;
            this.butNew.Click += new System.EventHandler(this.butNew_Click);
            // 
            // button1
            // 
            this.button1.Font = new System.Drawing.Font("Arial", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(204)));
            this.button1.Location = new System.Drawing.Point(320, 251);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(160, 40);
            this.button1.TabIndex = 1;
            this.button1.Text = "ТОП-1";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // button2
            // 
            this.button2.Font = new System.Drawing.Font("Arial", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(204)));
            this.button2.Location = new System.Drawing.Point(320, 297);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(160, 40);
            this.button2.TabIndex = 2;
            this.button2.Text = "Как играть";
            this.button2.UseVisualStyleBackColor = true;
            // 
            // button3
            // 
            this.button3.Font = new System.Drawing.Font("Arial", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(204)));
            this.button3.Location = new System.Drawing.Point(320, 343);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(160, 40);
            this.button3.TabIndex = 3;
            this.button3.Text = "Выход";
            this.button3.UseVisualStyleBackColor = true;
            // 
            // Launcher
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 561);
            this.Controls.Add(this.pane);
            this.Location = new System.Drawing.Point(10, 10);
            this.Name = "Launcher";
            this.Text = "Launcher";
            this.pane.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel pane;
        private System.Windows.Forms.Button butNew;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button1;
    }
}

