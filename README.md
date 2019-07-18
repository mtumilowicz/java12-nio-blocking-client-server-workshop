# java12-nio-channel-client-server-workshop

_Reference_: https://medium.com/coderscorner/tale-of-client-server-and-socket-a6ef54a74763  
_Reference_: https://www.amazon.com/Java-Nio-Ron-Hitchens/dp/0596002882

# project description
* the main goal of this project is to show how to implement blocking server using java NIO
* on the workshop we will try to fix failing tests from `test/workshop` package by following steps and hints in
`java/workshop` package
* answers: `java/answers` package

# theory in a nutshell
* Java NIO - Java New IO (not Java Non-blocking IO)
* channels are a new, first-class Java I/O paradigm
* channel is a conduit that transports data efficiently between byte buffers and the entity on the other end 
of the channel
    * You can think of the “buffer” as a temporary storage place
    * “Channel” is the medium that transports bulk of data into and out of buffers and it can be viewed as an endpoint for communication. (For example if we take “SocketChannel” class, it reads from and writes to TCP sockets. But the data must be encoded in ByteBuffer objects for reading and writing.
* channel implementations vary radically between operating systems
* channels operate only on byte buffers
* I/O falls into two categories: file I/O and stream I/O
    * so there are two types of channels: file and socket
* socket channels can operate in nonblocking mode and are selectable (will be discussed in details in the other 
workshops)
* A good metaphor for a channel is a
  [pneumatic tube](https://en.wikipedia.org/wiki/Pneumatic_tube#In_money_transfer), 
  the type used at drive-up bank-teller windows. Your paycheck would be the information you're sending. 
  The carrier would be like a buffer. You fill the buffer (place your paycheck in the carrier), "write" the buffer to
  the channel (drop the carrier into the tube), and the payload is carried to the I/O service (bank
  teller) on the other end of the channel.
  The response would be the teller filling the buffer (placing your receipt in the carrier) and
  starting a channel transfer in the opposite direction (dropping the carrier back into the tube).
  The carrier arrives on your end of the channel (a filled buffer is ready for you to examine).
  You then flip the buffer (open the lid) and drain it (remove your receipt). You drive away and
  the next object (bank customer) is ready to repeat the process using the same carrier (Buffer)
  and tube (Channel) objects.
* sockets - we can get the input and output streams from the socket
    * In NIO based systems, instead of writing data onto output streams
      and reading data from input streams, we read and write data from “buffers”
    * 
* `SocketChannel.socket()` - retrieves a socket associated with this channel
* instead of asking for socket’s input and output streams, we are going to write data to the channel itself