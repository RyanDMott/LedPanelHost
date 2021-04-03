#include "SerialReceiver.hpp"
#include <Arduino.h>


using namespace towerccc::LED;

SerialReceiver::SerialReceiver()
{
	Serial.begin(baudRate);	
}

void SerialReceiver::Flush() {
	while(Serial.available() > 0 && Serial.read() != timeout);
}

bool SerialReceiver::Enquire() {
  long readsToWaitAfterEnq = 40000;
  int printed;
  if (Serial.available() <= 0)
  {
    // Serial.println("Nothing there.");          
    return false;
  }
    
  if (Serial.read() != sync)
  {
    // Serial.print("Open with sync, not ");
    // Serial.println((int)printed);
    return false;
  }    
    
  Serial.print(enquiry);

  int readsTaken = 0;
	while((printed = Serial.read()) == sync || (printed == -1))
    if (--readsToWaitAfterEnq)
		{
		  readsTaken++;
		  continue;
		}
		else
    {
      Serial.println("Waited for too many syncs");
      return false;
    }
    			
	if (printed != startOfTransmission)
  {
    // Serial.print(readsTaken);
    // Serial.print(int(printed));
    Serial.print("Should not follow a sync byte: '");
    Serial.print(int(printed));
    Serial.println("'.");
    return false;
  }
	return true;
}

bool SerialReceiver::FetchInto(char* bufferStart, int dataLengthPlusOne) {  
  // Flush();
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
