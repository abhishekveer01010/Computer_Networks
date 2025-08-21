# Computer_Networks

✅ What This Code Can Do
1. Encoding Mode

When you choose option 1, it:

Asks for the number of data bits

Accepts the bits (e.g., 1 0 1 1)

Calculates how many parity bits are needed

Outputs the encoded Hamming code

2. Decoding Mode

When you choose option 2, it:

Accepts a received bit array (e.g., with one bit flipped)

Calculates the syndrome to locate any error

Corrects the erroneous bit (if found)

Extracts and displays the original data bits

🧪 Example Run: Encode 1 0 1 1

User input:

Enter your choice (1 or 2): 1
Enter number of data bits: 4
Enter data bits (space separated): 1 0 1 1


Output:

Original data: 1011
Encoded data: 0110011


(Exactly matches the manual (7,4) Hamming code example from earlier)

💥 Example Run: Detect and Correct an Error

Let’s flip one bit of the above: 0110011 → 0111011 (bit 4 flipped)

User input:

Enter your choice (1 or 2): 2
Enter length of received data: 7
Enter received bits (space separated): 0 1 1 1 0 1 1


Output:

Received data: 0111011
Error detected at position: 4
Error corrected!
Corrected data: 0110011
Extracted original data: 1011


🎉 Works perfectly!

⚠️ Minor Notes / Enhancements

Bit Positioning Logic:
You're using this condition:

if ((i + 1 & (i)) != 0)


Which assumes that (i+1) is not a power of 2. That's fine, but a more readable way might be:

if (!isPowerOfTwo(i + 1))


Input Validation:
No checks for 0 or 1 during data entry — you might want to reject invalid bit inputs.

More User-Friendly Output:
You could print positions in human-readable (1-based) indexing if helpful for education.

Multi-bit Errors:
Your code does not detect 2-bit errors (which is expected — Hamming (7,4) can only correct 1-bit errors).
