---
order: 100
icon: rocket
tags: [guide]
---
#### Setting parameters

The model parameters can be set using the `parameter` class. The number of neurons in each layer of the network needs to be set. 
After that the learning rate for weights and bias needs to be set. The batch size for mini-batch propagation algorithm needs to be set. For stochastic gradient descent, it must be set to 1. For batch gradient descent, it must be set to the batchsize.
The current options for rectification functions are sigmoid function, relu, leaky relu, tanh and softplus. Custom rectification functions are currently not supported, but can be configured using source code. (More information related to this can be found in the parameter class code)


```java
parameter.setLayerArray(784, 32, 16, 16, 10);
parameter.setLearningRate(1);
parameter.setBiasLearningRate(1);
parameter.setBatchsize(10);
parameter.setRectificationFunction("sigmoid");
```

#### Training the model

The Trainer class is used to train all the data in the model. Creating a new trainer class automatically creates the required layers, neurons and weights, all of which are objects.
The trainer class can be trained with required number of samples and epochs. Testing of the data can be done with required number of samples. Testing and training of the data is done from two different files.

```java
Trainer myTrainer = new Trainer();
		// Training the network with 45000 samples for 10 epochs
		myTrainer.train(45000, 10);
    myTrainer.test(9990);
```

#### Observing each neuron
Neurons can be observed using The neuron observer class. Everytime the observed neuron is updated, the results are displayed.

```java
NeuronObserver myNeuronObserver = new NeuronObserver();
myNeuronObserver.setModel(myTrainer.getLayerManager());
myNeuronObserver.addNeuronToBeObserved(1, 31);
```


_______ 

> Want more details?
> Check out the [README file](https://github.com/Aatmaj-Zephyr/ANN4j/tree/main#readme) for complete details.