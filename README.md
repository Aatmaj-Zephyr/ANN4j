<p align="center">
<img width="600" align="center" src="https://user-images.githubusercontent.com/83284294/205426515-b272d188-2902-42d7-aa79-defdf3f073f7.png">
</p>

---

### Artificial Neural Networks for Java

This package provides Object oriented Neural Networks for making _Explainable Networks_. Object Oriented Network structure is helpful for observing each and every element the model. This package is developed for XAI research and development.


#### Features 
- Observable implementation for Artificial Neural Networks (ANN)
- XAI method for relevance propagation
- Stochastic/batch gradient descent
- No hardcoded implementations lets researchers change the parameters as they want.
- Plug and play mnist type data. Other Data files can be handeled via extension






### Usage

 `import ann4j.*;`


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

#### Evaluating the model

- Training accuracy
```java
myTrainer.getModelEvaluator().getTrainingAccuracy();
```

- Testing accuracy
```java
myTrainer.getModelEvaluator().getTrainingAccuracy();
```

- Confusion Matrix

```java
myTrainer.getModelEvaluator().printConfusionMatrix();
```


#### XAI



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


#### Examples

- Example code https://github.com/Aatmaj-Zephyr/ANN4j/blob/main/Main.java
- Sample output https://github.com/Aatmaj-Zephyr/ANN4j/blob/3721148ec24371bf095e1394fe39fc471f391466/output.txt

- Sample output https://github.com/Aatmaj-Zephyr/ANN4j/blob/ef0f34b505e6e6316f94b5a660b9ef651582667d/output.txt

#### Other resources

- More about Artificial Neural Networks https://www.3blue1brown.com/topics/neural-networks
- Relevance propagation example https://towardsdatascience.com/indepth-layer-wise-relevance-propagation-340f95deb1ea
- Rectification functions https://www.quora.com/What-is-the-purpose-of-rectifier-functions-in-neural-networks


