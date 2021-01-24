#ifndef SERIAL_RECIEVER_H
#define SERIAL_RECIEVER_H
namespace towerccc
{ 
	namespace LED
	{
		class SerialReceiver
		{
		public:	
			SerialReceiver();
			// WARNING needs one extra byte at end of buffer!
			bool FetchInto(char* bufferStart, int dataLengthPLusOne);
			
			// WaitForFetchFrom()
		private:
			const long baudRate = 115200;
      // const long baudRate = 9600;
			
			const char startOfTransmission = 2;
			const char endOfTransmission = 3;
			const char enquiry = 5;
			const char acknowledge = 6;
			const char notAcknowldedge = 21;
			const char sync = 22;
			const int  timeout = -1;

			void Flush();
			bool Enquire();		
		};
	}
}
#endif
