from keras.models import model_from_json

with open('model.json','r') as f:
    model = model_from_json(f.read())
    
model.load_weights('model.h5')