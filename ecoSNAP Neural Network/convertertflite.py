# Convert the model.
import tensorflow as tf
from keras.models import load_model
model = load_model('new-model.h5')

converter = tf.lite.TFLiteConverter.from_keras_model(model)
tflite_model = converter.convert()
