#include "SerialReceiver.hpp"
#include <Arduino.h>


using namespace towerccc::LED;

SerialReceiver::SerialReceiver()
{
	Serial.begin(baudRate);	
}

void SerialReceiver::Flush() {
	while(Serial.read() != timeout);
}

bool SerialReceiver::Enquire() {
	Serial.print(enquiry);
	long readsToWaitAfterEnq = 20000;
	while(Serial.read() != startOfTransmission)
		if (--readsToWaitAfterEnq)
			continue;
		else
			return false;
	
	return true;
}

bool SerialReceiver::FetchInto(char* bufferStart, int dataLengthPlusOne) {
	Flush();
	if (!Enquire())
		return false;
	
	int bytesBack = Serial.readBytes(bufferStart, dataLengthPlusOne);
	byte closeByte = bufferStart[dataLengthPlusOne - 1];
	
	if ( bytesBack == dataLengthPlusOne 
		&& closeByte == endOfTransmission)
	{
		Serial.print(acknowledge);
		return true;
	}
	else
	{
		Serial.print( notAcknowldedge );
		Serial.print("Got ");
		Serial.print(bytesBack - 1);
		Serial.print(" data bytes. Expected ");
		Serial.print(dataLengthPlusOne - 1);
		Serial.print(". Close byte: ");
		Serial.print((int) closeByte);
		Serial.print(". First data: ");
		Serial.print((int)bufferStart[0]);
		Serial.print(". Last data: ");
		Serial.print((int)bufferStart[bytesBack-2]);
		Serial.print(". First ten bytes of data as ints: ");
		for (int i = 0; i < 10; i++)
    {
			Serial.print((int)bufferStart[i]);
      Serial.print(" ");
    }
		// Serial.print(bufferStart);
		Serial.println(".");
		return false; 
	}
}
