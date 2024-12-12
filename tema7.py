import tkinter as tk
from tkinter.ttk import Combobox
import random
import time

class SortingVisualizer:
    def __init__(self, root):
        self.root = root
        self.root.title("Sorting Algorithm Visualizer")

        # UI Elements
        self.algorithm_label = tk.Label(root, text="Select Algorithm:")
        self.algorithm_label.pack()

        self.algorithm_combo = Combobox(root, values=["Bubble Sort"], state="readonly")
        self.algorithm_combo.pack()
        self.algorithm_combo.current(0)

        self.speed_label = tk.Label(root, text="Select Speed:")
        self.speed_label.pack()

        self.speed_scale = tk.Scale(root, from_=1, to=10, orient=tk.HORIZONTAL, label="Speed")
        self.speed_scale.pack()

        self.element_label = tk.Label(root, text="Number of Elements:")
        self.element_label.pack()

        self.element_scale = tk.Scale(root, from_=5, to=100, orient=tk.HORIZONTAL, label="Elements")
        self.element_scale.pack()

        self.randomize_button = tk.Button(root, text="Randomize", command=self.randomize)
        self.randomize_button.pack()

        self.start_button = tk.Button(root, text="Start", command=self.start_sorting)
        self.start_button.pack()

        self.reset_button = tk.Button(root, text="Reset", command=self.reset)
        self.reset_button.pack()

        self.quit_button = tk.Button(root, text="Exit", command=root.quit)
        self.quit_button.pack()

        # Canvas for visualization
        self.canvas = tk.Canvas(root, width=800, height=400, bg="white")
        self.canvas.pack()

        self.data = []

    def randomize(self):
        num_elements = self.element_scale.get()
        self.data = [random.randint(1, 100) for _ in range(num_elements)]
        self.draw_bars()

    def draw_bars(self, highlight=None):
        self.canvas.delete("all")
        canvas_width = 800
        canvas_height = 400
        bar_width = canvas_width / len(self.data)
        max_height = max(self.data, default=1)

        for i, value in enumerate(self.data):
            x0 = i * bar_width
            y0 = canvas_height - (value / max_height) * canvas_height
            x1 = (i + 1) * bar_width
            y1 = canvas_height
            color = "blue" if highlight and i in highlight else "gray"
            self.canvas.create_rectangle(x0, y0, x1, y1, fill=color)

        self.root.update()

    def start_sorting(self):
        algorithm = self.algorithm_combo.get()
        speed = self.speed_scale.get()

        if algorithm == "Bubble Sort" and self.data:
            self.bubble_sort(speed)

    def bubble_sort(self, speed):
        n = len(self.data)
        for i in range(n):
            for j in range(0, n - i - 1):
                self.draw_bars(highlight=[j, j + 1])
                self.root.update()
                time.sleep(1 / speed)
                if self.data[j] > self.data[j + 1]:
                    self.data[j], self.data[j + 1] = self.data[j + 1], self.data[j]
        self.draw_bars()

    def reset(self):
        self.data = []
        self.canvas.delete("all")

if __name__ == "__main__":
    root = tk.Tk()
    app = SortingVisualizer(root)
    root.mainloop()
