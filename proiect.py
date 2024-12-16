import tkinter as tk
from tkinter import ttk
import random
import time


class SortingVisualizer:
    def __init__(self, root):
        self.root = root
        self.root.title("Sorting Algorithm Visualizer")

        self.algorithms = {
            "Bubble Sort": self.bubble_sort,
            "Insertion Sort": self.insertion_sort,
            "Selection Sort": self.selection_sort,
            "Bogo Sort": self.bogo_sort
        }

        self.speed_options = {
            "Very Slow": 1,
            "Slow": 0.5,
            "Normal": 0.2,
            "Fast": 0.05,
            "Very Fast": 0.01
        }

        self.data = []
        self.create_ui()

    def create_ui(self):
        # Frame for controls
        self.controls_frame = tk.Frame(self.root, padx=10, pady=10)
        self.controls_frame.pack(fill=tk.X)

        # Algorithm selection
        tk.Label(self.controls_frame, text="Algorithm:").pack(side=tk.LEFT)
        self.algorithm_menu = ttk.Combobox(self.controls_frame, values=list(self.algorithms.keys()), state="readonly")
        self.algorithm_menu.pack(side=tk.LEFT, padx=5)
        self.algorithm_menu.current(0)

        # Speed selection
        tk.Label(self.controls_frame, text="Speed:").pack(side=tk.LEFT)
        self.speed_menu = ttk.Combobox(self.controls_frame, values=list(self.speed_options.keys()), state="readonly")
        self.speed_menu.pack(side=tk.LEFT, padx=5)
        self.speed_menu.current(2)

        # Number of elements
        tk.Label(self.controls_frame, text="Elements:").pack(side=tk.LEFT)
        self.elements_scale = tk.Scale(self.controls_frame, from_=5, to_=100, orient=tk.HORIZONTAL)
        self.elements_scale.pack(side=tk.LEFT, padx=5)

        # Buttons
        tk.Button(self.controls_frame, text="Generate", command=self.generate_data).pack(side=tk.LEFT, padx=5)
        tk.Button(self.controls_frame, text="Start", command=self.start_sorting).pack(side=tk.LEFT, padx=5)
        tk.Button(self.controls_frame, text="Reset", command=self.reset).pack(side=tk.LEFT, padx=5)
        tk.Button(self.controls_frame, text="Exit", command=self.root.quit).pack(side=tk.LEFT, padx=5)

        # Canvas for visualization
        self.canvas = tk.Canvas(self.root, height=400, bg="white")
        self.canvas.pack(fill=tk.BOTH, expand=True, padx=10, pady=10)

    def generate_data(self):
        self.data = [random.randint(1, 100) for _ in range(self.elements_scale.get())]
        self.draw_data()

    def draw_data(self, highlighted_indices=None):
        self.canvas.delete("all")
        canvas_width = self.canvas.winfo_width()
        canvas_height = self.canvas.winfo_height()
        bar_width = canvas_width / len(self.data)
        max_height = max(self.data)

        for i, value in enumerate(self.data):
            x0 = i * bar_width
            y0 = canvas_height - (value / max_height * canvas_height)
            x1 = (i + 1) * bar_width
            y1 = canvas_height
            color = "red" if highlighted_indices and i in highlighted_indices else "blue"
            self.canvas.create_rectangle(x0, y0, x1, y1, fill=color)
        self.root.update_idletasks()

    def start_sorting(self):
        algorithm_name = self.algorithm_menu.get()
        speed = self.speed_menu.get()
        self.sorting_speed = self.speed_options[speed]
        self.algorithms[algorithm_name]()

    def bubble_sort(self):
        for i in range(len(self.data) - 1):
            for j in range(len(self.data) - i - 1):
                self.draw_data([j, j + 1])
                time.sleep(self.sorting_speed)
                if self.data[j] > self.data[j + 1]:
                    self.data[j], self.data[j + 1] = self.data[j + 1], self.data[j]
        self.draw_data()

    def insertion_sort(self):
        for i in range(1, len(self.data)):
            key = self.data[i]
            j = i - 1
            while j >= 0 and key < self.data[j]:
                self.data[j + 1] = self.data[j]
                j -= 1
                self.draw_data([j, j + 1])
                time.sleep(self.sorting_speed)
            self.data[j + 1] = key
        self.draw_data()

    def selection_sort(self):
        for i in range(len(self.data)):
            min_idx = i
            for j in range(i + 1, len(self.data)):
                self.draw_data([min_idx, j])
                time.sleep(self.sorting_speed)
                if self.data[j] < self.data[min_idx]:
                    min_idx = j
            self.data[i], self.data[min_idx] = self.data[min_idx], self.data[i]
        self.draw_data()

    def bogo_sort(self):
        while not self.is_sorted():
            random.shuffle(self.data)
            self.draw_data()
            time.sleep(self.sorting_speed)

    def is_sorted(self):
        return all(self.data[i] <= self.data[i + 1] for i in range(len(self.data) - 1))

    def reset(self):
        self.data = []
        self.draw_data()


if __name__ == "__main__":
    root = tk.Tk()
    app = SortingVisualizer(root)
    root.mainloop()
