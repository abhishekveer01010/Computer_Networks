import random
import time

class GoBackN:
    def __init__(self, window_size, total_frames):
        self.window_size = window_size
        self.total_frames = total_frames
        self.frames = list(range(1, total_frames + 1))
        self.base = 0
        self.next_seq_num = 0

    def send(self):
        while self.base < self.total_frames:
            # Send frames in the window
            while self.next_seq_num < self.base + self.window_size and self.next_seq_num < self.total_frames:
                print(f"Sender: Sending frame {self.frames[self.next_seq_num]}")
                self.next_seq_num += 1

            # Simulate ACK or packet loss
            ack = self.receive_ack()

            if ack is not None:
                print(f"Receiver: ACK received for frame {ack}")
                self.base = ack
            else:
                print("Timeout! Resending window...")
                self.next_seq_num = self.base

            print("-" * 40)
            time.sleep(1)

    def receive_ack(self):
        # Randomly simulate ACK loss
        if random.choice([True, False]):
            # Receiver acknowledges the next expected frame
            return self.next_seq_num
        else:
            return None


if __name__ == "__main__":
    gbn = GoBackN(window_size=4, total_frames=10)
    gbn.send()
