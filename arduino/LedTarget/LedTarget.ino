#include "SerialReceiver.hpp"
#include <FastLED.h>

using namespace towerccc::LED;

#define LED_COUNT (8*32)
#define BYTES_PER_LED 3
#define BUFFER_SIZE (LED_COUNT * BYTES_PER_LED + 1)

#define CHIPSET     WS2811
#define COLOR_ORDER GRB
CRGB leds[LED_COUNT];

#define LED_PIN     3

// NeoPixel brightness, 0 (min) to 255 (max)
#define BRIGHTNESS 16

void setup() {
  delay( 3000 ); // power-up safety delay
  FastLED.addLeds<CHIPSET, LED_PIN, COLOR_ORDER>(leds, LED_COUNT).setCorrection( TypicalSMD5050 );
  FastLED.setBrightness( BRIGHTNESS );

  // put your setup code here, to run once:
  
  for(int i = 0; i < LED_COUNT; i++)
    leds[i] = CRGB(0,0,0);

  FastLED.show();
    
  SerialReceiver receiver = SerialReceiver();
  byte buffer[BUFFER_SIZE];
  while(true)
  {
    if (!receiver.FetchInto(buffer, BUFFER_SIZE))
      continue;

    int i;
    for(i = 0; i < LED_COUNT; i++)
    {
      leds[i].red = buffer[3*i+0];
      leds[i].green = buffer[3*i+1];
      leds[i].blue = buffer[3*i+2];     
    }
  
    FastLED.show();
    break;
  }  
}

void loop() {
  FastLED.delay(8000);
}
