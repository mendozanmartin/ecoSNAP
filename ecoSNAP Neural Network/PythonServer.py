# MLP for Pima Indians Dataset Serialize to JSON and HDF5
from keras.models import Sequential
from keras.layers import Dense
from keras.models import model_from_json
from keras.preprocessing.image import ImageDataGenerator
from keras.applications.mobilenet import preprocess_input
import base64
from pyngrok import ngrok


import os
import http.server
import socketserver
import requests

# load json and create model
json_file = open('model.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)
# load weights into new model
loaded_model.load_weights("model.h5")
print("Loaded model from disk")
 
# evaluate loaded model on test data
loaded_model.compile(loss='binary_crossentropy', optimizer='rmsprop', metrics=['accuracy'])
train_datagen=ImageDataGenerator(preprocessing_function=preprocess_input) #included in our dependencies

training_set=train_datagen.flow_from_directory('dataset/dataset/training_set',
                                                 target_size=(224,224),
                                                 color_mode='rgb',
                                                 batch_size=32,
                                                 class_mode='categorical',
                                                 shuffle=True)

# Part 3 - Making new predictions
from flask import Flask, jsonify, request
import numpy as np
from keras.preprocessing import image
import keras.backend.tensorflow_backend as tb


def makePrediction(imageUrl):
    tb._SYMBOLIC_SCOPE.value = True
    test_image = image.load_img(imageUrl, target_size = (64, 64))
    test_image = image.img_to_array(test_image)
    test_image = np.expand_dims(test_image, axis = 0)
    training_set.class_indices
    result = loaded_model.predict(test_image)
    arr2D = np.array(result)
    result = np.where(arr2D == np.amax(arr2D))
    print(result)
    return {'data': "Hello"}

app = Flask(__name__) 

@app.route('/', methods = ['POST'])
def home(): 
    jpgtxt = request.form['url']
    jpgtxt = base64.encodestring(open("in.jpg","rb").read())
    
    f = open("jpg1_b64.txt", "w")
    f.write(jpgtxt)
    f.close()
    
    newjpgtxt = open("jpg1_b64.txt","rb").read()
    
    g = open("output.jpg", "w")
    g.write(base64.decodestring(newjpgtxt))
    g.close()
    return makePrediction("output.jpg") 
      


# driver function 
if __name__ == '__main__': 
  
    app.run(debug = True, port=80) 
