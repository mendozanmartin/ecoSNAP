import tensorflow as tf

converter = tf.lite.TFLiteConverter.from_keras_model("model.h5")
converter.experimental_new_converter = True
tflite_model = converter.convert()
open ("model.tflife","wb").write(tflite_model)
