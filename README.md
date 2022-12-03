<p align="center">
<img width="600" align="center" src="https://user-images.githubusercontent.com/83284294/205426515-b272d188-2902-42d7-aa79-defdf3f073f7.png">
</p>

---

### Artificial Neural Networks for Java

This package provides Object oriented Neural Networks for making _Explainable Networks_. Object Oriented Network structure is helpful for observing each and every element the model. This package is developed for XAI research and development.

### Usage

import `import ann4j.*;`


#### Setting parameters

- Setting the output file to be output.txt and enabling command line logging

```java
		parameter.setOutputFile("output.txt", true);
```

- Setting the number of neurons in each layer.

  ```java
  	parameter.setLayerArray(784, 32, 16, 16, 26);
  ```

- Setting the training file to be emnist-letters-train.csv and the file type
  ````java
  	parameter.setTrainingFileReader("emnist-letters-train.csv", "mnist");

  ````
- Setting the testing file

  ````java
  	parameter.setTestingFileReader("emnist-letters-test.csv", "mnist");

  ````

- Setting the learning rate for weights

  ````java
  	parameter.setLearningRate(1);

  ````

- Setting the learning rate for the bias to 1.

  ````java
  	parameter.setBiasLearningRate(1);

  ````

- Setting the epsillion value for the relevance propagation algorithm.
  ```java
  	parameter.setEpsillion(0);
  ```
- Setting the batch size

  ```java
  	parameter.setBatchsize(10);

  ```

- Setting the rectification function.

  ```java
  	parameter.setRectificationFunction("sigmoid");

  ```


#### Training the Model

- Creating a new instance of the Trainer class.
  ```java
  	Trainer myTrainer = new Trainer();

  ```
- Training the network with 88800 samples for n epochs
  ```java
  	myTrainer.train(m, n);

  ```
- Creating a new instance of the NeuronObserver class this class will observe the neurons and respond when every parameter is changed.
  ```java
  	NeuronObserver myNeuronObserver = new NeuronObserver();

  ```
- Testing the network with 9990 samples.

  ````java
  	myTrainer.test(9990);


  ````

- Adding the neuron at layer 1 and index 31 to be observed.
  ```java
  	myNeuronObserver.addNeuronToBeObserved(1, 31);

  ```

#### XAI

- confusion matrix

```java
myTrainer.printConfusionMatrix();
```

- xai algorithm for relevance propagation.
  ```java
  	myTrainer.relevancePropagate(2, 3);
  ```
- xai algotithm for most significant input neurons

  ```java
  	myTrainer.forwardPropagatewithExclusionInputLayerOnKSamples(2);

  ```


#### File rendering

ANN4j provides functionality to extend the InputFileReader to add file handling for various types of datasets apart from mnist type files.


#### Observable methods

In ANN4j, every neuron is an object of its own. Every Neuron can be observed by the NeuronObserver class when the values are updated. NeuronObserver class can be extended as per the requirement of the parameters to be observed. Neurons objects can also be obtined and observed independantly.

- Get a neuron object from a layer.
```java
myTrainer.getLayerManager().getLayer(layerNum).getNeuron(neuronNum));
```
- Get activation of a neuron
```java
 neuron.getActivation();
 ```

- Get bias of the neuron
```java 
 neuron.getBias();
 ```

 - Get arraylist of the left or right connections of the neuron
 ```java
 neuron.leftConnections;
 neuron.rightConnections;
```

- Get weight of a connection
```java
connection.getWeight();
```