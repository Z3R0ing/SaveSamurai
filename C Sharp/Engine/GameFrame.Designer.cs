namespace SaveSamurai.Engine
{
    partial class GameFrame
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.SuspendLayout();
            // 
            // GameFrame
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(784, 561);
            this.Location = new System.Drawing.Point(10, 10);
            this.Name = "GameFrame";
            this.Text = "Save Samurai";
            this.Activated += new System.EventHandler(this.GameFrame_Activated);
            this.Deactivate += new System.EventHandler(this.GameFrame_Deactivate);
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.GameFrame_FormClosing);
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.GameFrame_FormClosed);
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.GameFrame_KeyDown);
            this.ResumeLayout(false);

        }

        #endregion
    }
}