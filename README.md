# text-steganography
my final year undergraduate project.

The model consists of four building blocks: Encipher function, which enciphers a message using one-time pad scheme, Hide function, which conceals the scrambled message using a stego key, Seek function, which extracts the hidden information from the stego file using the stego key, and, Decipher function, which deciphers the extracted message using the secret key. 
In all algorithms, I have assumed integer division, i.e. division by a whole number ignores the fractional part of the result.

I used one-time padthe key, comprising of true random numbers, which is used for enciphering a message is as long as the message. Moreover, a key is used for single encryption-decryption.
Thus, there is no fixed pattern which an eavesdropper can analyze.


