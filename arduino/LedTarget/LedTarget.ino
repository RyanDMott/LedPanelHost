#include "SerialReceiver.hpp"

using namespace towerccc::LED;

#define LED_COUNT (8*32)
#define BYTES_PER_LED 3
#define BUFFER_SIZE (LED_COUNT * BYTES_PER_LED + 1)
// #define BUFFER_SIZE (10 + 1)

void setup() {
  // put your setup code here, to run once:
  SerialReceiver receiver = SerialReceiver();
  byte buffer[BUFFER_SIZE];
  while(true)
  {
    // receiver.FetchInto(buffer, LED_COUNT + 1);
    receiver.FetchInto(buffer, BUFFER_SIZE);
  }  
}

void loop() {
  // put your main code here, to run repeatedly:
  
}
