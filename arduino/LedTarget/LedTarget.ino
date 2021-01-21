#include "SerialReceiver.hpp"
#include <Adafruit_NeoPixel.h>

using namespace towerccc::LED;

#define LED_COUNT (8*32)
#define BYTES_PER_LED 3
#define BUFFER_SIZE (LED_COUNT * BYTES_PER_LED + 1)
// #define BUFFER_SIZE (10 + 1)

#ifdef __AVR__
 #include <avr/power.h> // Required for 16 MHz Adafruit Trinket
#endif

// Which pin on the Arduino is connected to the NeoPixels?
// On a Trinket or Gemma we suggest changing this to 1:
#define LED_PIN     3

// NeoPixel brightness, 0 (min) to 255 (max)
#define BRIGHTNESS 16

// Declare our NeoPixel strip object:
// Adafruit_NeoPixel strip(LED_COUNT, LED_PIN, NEO_GRBW + NEO_KHZ800);
Adafruit_NeoPixel strip(LED_COUNT, LED_PIN, NEO_GRB + NEO_KHZ800);
// Argument 1 = Number of pixels in NeoPixel strip
// Argument 2 = Arduino pin number (most are valid)
// Argument 3 = Pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)
//   NEO_RGBW    Pixels are wired for RGBW bitstream (NeoPixel RGBW products)


void setup() {
  // put your setup code here, to run once:
  strip.begin();           // INITIALIZE NeoPixel strip object (REQUIRED)
  strip.show();            // Turn OFF all pixels ASAP
  strip.setBrightness(BRIGHTNESS);
  SerialReceiver receiver = SerialReceiver();
  byte buffer[BUFFER_SIZE];
  while(true)
  {
    // receiver.FetchInto(buffer, LED_COUNT + 1);
    receiver.FetchInto(buffer, BUFFER_SIZE);

    int i;
    for(i = 0; i < LED_COUNT; i++)
    {
      strip.setPixelColor(i, strip.gamma32(strip.Color(
        buffer[3*i+0], 
        buffer[3*i+1], 
        buffer[3*i+2]))); // add fourth element (zero)*/
    }
  
    strip.show();
  }  
}

void loop() {
  // put your main code here, to run repeatedly:
  
}
