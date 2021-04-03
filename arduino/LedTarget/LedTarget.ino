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

#define INDICATOR_PIN 13

// NeoPixel brightness, 0 (min) to 255 (max)
#define BRIGHTNESS 4

bool ledState;

void toggleLedState()
{
  digitalWrite(INDICATOR_PIN, ledState = !ledState);
}

void setup() {
  ledState = true;
  pinMode(INDICATOR_PIN, OUTPUT);
  toggleLedState();
  delay( 3000 ); // power-up safety delay
  toggleLedState();
  FastLED.addLeds<CHIPSET, LED_PIN, COLOR_ORDER>(leds, LED_COUNT);
  FastLED.setBrightness( BRIGHTNESS );

  // put your setup code here, to run once:
  
  for(int i = 0; i < LED_COUNT; i++)
    leds[i] = CRGB(1,0,0);

  FastLED.show();
  FastLED.delay(1000);
  
  SerialReceiver receiver = SerialReceiver();
  
  while(true)
  {    
    FastLED.delay(100);
    // delay(100);
    toggleLedState();
    if (!receiver.FetchInto((byte*) &(leds[0].red), BUFFER_SIZE))
      continue;      

    // Cheap (ie. fast) gamma correction (contrast adjustment) that can be run right before calling FastLED.show()
    for (uint16_t i = 0; i < LED_COUNT; i++)
    {
      leds[i].r = dim8_video(leds[i].r);
      leds[i].g = dim8_video(leds[i].g);
      leds[i].b = dim8_video(leds[i].b);
    }

    FastLED.show();    
  }  
}

void loop() {
  FastLED.delay(100);
  toggleLedState();
}
