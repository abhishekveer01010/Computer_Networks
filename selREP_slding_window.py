import random
import time

class SelectiveRepeat:
    def __init__(self, window_size, total_frames):
        self.window_size = window_size
        self.total_frames = total_frames
        self.frames = list(range(1, total_frames + 1))
        self.base = 0
        self.next_seq_num = 0
        self.acks = [False] * total_frames

    def send(self):
        while self.base < self.total_frames:
            # Send frames in the window
            while self.next_seq_num < self.base + self.window_size and self.next_seq_num < self.total_frames:
                print(f"Sender: Sending frame {self.frames[self.next_seq_num]}")
                self.next_seq_num += 1

            # Simulate ACKs
            self.receive_ack()

            # Slide window if base frame is acknowledged
            while self.base < self.total_frames and self.acks[self.base]:
                self.base += 1

            print("-" * 40)
            time.sleep(1)

    def receive_ack(self):
        # For all frames in the window, randomly decide if ack is received
        for i in range(self.base, min(self.base + self.window_size, self.total_frames)):
            if not self.acks[i]:  # Not acknowledged yet
                if random.choice([True, False]):
                    self.acks[i] = True
                    print(f"Receiver: ACK received for frame {self.frames[i]}")
                else:
                    print(f"Receiver: Frame {self.frames[i]} lost! Resending...")
                    print(f"Sender: Resending frame {self.frames[i]}")


if __name__ == "__main__":
    sr = SelectiveRepeat(window_size=4, total_frames=10)
    sr.send()
