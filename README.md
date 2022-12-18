<p align="center">
<img width="600" align="center" src="https://user-images.githubusercontent.com/83284294/205426515-b272d188-2902-42d7-aa79-defdf3f073f7.png">
</p>

---

### ANN4j - Artificial Neural Networks for Java

ANN4j is a java package that provides Object oriented Neural Networks for making _Explainable Networks_. Object Oriented Network structure is helpful for observing each and every element the model. This package is developed for XAI research and development.


#### Features 
- Observable implementation for Artificial Neural Networks (ANN)
- XAI method for relevance propagation
- Stochastic/batch gradient descent
- No hardcoded implementations lets researchers change the parameters as they want.
- Plug and play mnist type data. Other Data files can be handeled via extension


#### ANN4j - Creating observable object-oriented neural networks for better Explainable AI.
ANN4j is a java package that provides object oriented functionality to neural networks. It implements multilayer perceptrons in java by using Objects instead of matrix multiplications. Every neuron is treated as a seperate object. While this kind of implementation is highly inefficiant when compared to matrix multiplications, this implementation will help research in the fields of Explainable AI. Explainable AI aims at making the model interpretable. By pausing and observing the neural net at different stages, researchers can study neural networks more efficiantly. Indivisual observable interfaces are more easy to observe then matrices. Operations which are difficult to perform on matrices can be performed more easily using this technique. 

## Table of Contents

- [Usage](#usage)
  - [Download](#download)
  - [Requirements and Dependencies](#requirements-and-dependancies)
- [Training](#training)
  - [Parameters](#setting-parameters)
  - [Training the Model](#training-the-model)
  - [Evaluating the model](#evaluating-the-model)
  - [Explanable AI](#xai)
  - [Observable Methods](#observable-methods)
- [Data Format](#data-format)
   - [Default Format](#default-format)
   - [Other Format](#file-rendering-for-other-formats)
      - [Constructor](#constructor)
      - [Getting Input](#getting-input)
      - [Getting Label](#getting-label)
      - [Getting Input Neuron values](#getting-input-neuron-values)
      - [Getting Output Neuron values](#getting-output-neuron-values)
      - [Getting prediction from the output neurons](#getting-prediction-from-the-output-neurons)
      - [Restarting the FileReader](#Restarting-the-file)
- [Examples](#examples)
    - [Other Resources](#other-resources)
- [ANN4j Community](#ann4j-community)
	- [Raising an issue](#raising-an-issue)
  - [Asking for help](#asking-for-help)
  - [Community](#community)
  - [Contributing](#contributing)
  - [Help spread the word](#help-spread-the-word)
  - [Cite](#citing-this-package-for-research-work)
- [License](#license)

___



### Usage


#### Download
 [Releases](https://github.com/Aatmaj-Zephyr/ANN4j/releases)
 
The package can be imported after download.
 `import ann4j.*;`

#### Requirements and Dependencies
None. This package is made using 100% Pure Java. The java package requires java 5.0+. No other requirements are required. Recommended to use the latest version of Java. [Java download link](https://www.java.com/download/ie_manual.jsp)

### Training
#### Setting parameters

- Setting the output file to be output.txt and enabling command line logging.

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


### Data format

#### Default format
The default format for the package is MNIST format. 

File type CSV consisting of the following
- 1 Label (Expected number)
- n pixel weights
n must match number of input neurons.

Example 
```
2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,116,125,171,255,255,150,93,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,169,253,253,253,253,253,253,218,30,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,169,253,253,253,213,142,176,253,253,122,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,52,250,253,210,32,12,0,6,206,253,140,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,77,251,210,25,0,0,0,122,248,253,65,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,31,18,0,0,0,0,209,253,253,65,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,117,247,253,198,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,76,247,253,231,63,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,128,253,253,144,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,176,246,253,159,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,25,234,253,233,35,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,198,253,253,141,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,78,248,253,189,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,19,200,253,253,141,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,134,253,253,173,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,248,253,253,25,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,248,253,253,43,20,20,20,20,5,0,5,20,20,37,150,150,150,147,10,0,0,0,0,0,0,0,0,0,248,253,253,253,253,253,253,253,168,143,166,253,253,253,253,253,253,253,123,0,0,0,0,0,0,0,0,0,174,253,253,253,253,253,253,253,253,253,253,253,249,247,247,169,117,117,57,0,0,0,0,0,0,0,0,0,0,118,123,123,123,166,253,253,253,155,123,123,41,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
```

<img width="200" alt="image" src="https://user-images.githubusercontent.com/83284294/208296890-8655f164-ffe5-41b3-865d-c28c909ded2c.png">

Here the image 2 is represented as an array of 28*28 pixels each value represents pixel activation.


Some datasets to test the package on (without extending mnist file reader)

1) [MNIST Handwritten database](https://www.kaggle.com/datasets/oddrationale/mnist-in-csv)
2) [MNIST extended chracter database](https://www.nist.gov/itl/products-and-services/emnist-dataset) and [Kaggle link](https://www.kaggle.com/datasets/crawford/emnist)
3) [MNIST fashion data set](https://www.kaggle.com/datasets/zalando-research/fashionmnist)
4) [Kannada MNIST](https://www.kaggle.com/competitions/Kannada-MNIST/data)


References 
- [About MNIST database](https://en.wikipedia.org/wiki/MNIST_database)

- [Preparing MNIST data](https://visualstudiomagazine.com/articles/2022/02/01/preparing-mnist-image-data-text-files.aspx#:~:text=The%20MNIST%20Modified%20National%20Institute,integer%20between%200%20and%20255)


#### File rendering for other formats

ANN4j provides functionality to extend the InputFileReader to add file handling for various types of datasets apart from mnist type files. InputFileReader or MNISTFileReader can be extended by making relevant changes in file reading functions. 

##### Constructor
The new file reader class must pass the filename to the super constructor.
```java
    super(filename);
```

#### Getting input
The `next()` method is responsible for reading new line input from the dataset. It must also act as a super setter method. It must set all values like `label` , `expectedOutputArray` and `inputArray`
```java
    public void next()
```

#### Getting label
This method must return the label (expected value of prediction).

```java
    public double getLabel()
```

#### Getting Input Neuron values
This method must return the values of neurons (expected value of prediction). Example for digit recognition fo digit two, the arraylist must contain 784 elements of the pixel values.
```
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,116,125,171,255,255,150,93,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,169,253,253,253,253,253,253,218,30,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,169,253,253,253,213,142,176,253,253,122,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,52,250,253,210,32,12,0,6,206,253,140,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,77,251,210,25,0,0,0,122,248,253,65,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,31,18,0,0,0,0,209,253,253,65,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,117,247,253,198,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,76,247,253,231,63,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,128,253,253,144,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,176,246,253,159,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,25,234,253,233,35,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,198,253,253,141,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,78,248,253,189,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,19,200,253,253,141,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,134,253,253,173,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,248,253,253,25,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,248,253,253,43,20,20,20,20,5,0,5,20,20,37,150,150,150,147,10,0,0,0,0,0,0,0,0,0,248,253,253,253,253,253,253,253,168,143,166,253,253,253,253,253,253,253,123,0,0,0,0,0,0,0,0,0,174,253,253,253,253,253,253,253,253,253,253,253,249,247,247,169,117,117,57,0,0,0,0,0,0,0,0,0,0,118,123,123,123,166,253,253,253,155,123,123,41,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
```

```java
    public ArrayList<Double> getInputArray()
```

#### Getting Output Neuron values
This method must return the expected values of output neurons (expected value of prediction). Example for digit recognition fo digit two, the arraylist can be 
`0,0,1,0,0,0,0,0,0,0`


This is dependant on the model and is a design decision.


```java
    public ArrayList<Double> getExpectedOutputArray() 
```

#### Getting prediction from the output neurons
This method is used for obtaining the prediction value from the activations in the output neurons. In the digit recognitoin case as every input is mapped with same neuron it is the same. For example if neuron number 3 fires the highest, the model has predicted 3. But this needs to be overridden for different model configurations.

```java
    public double getPredictionFromNeuronNum(int mostSignificantNeuronNumAsPrediction)
```


Note- 

Predicted neuron and prediction are different. 

Predicted neuron is the neuron which is most significant in firing. The prediction is the value corresponding to that neuron.

Example Consider case of handwritten letters database. If the neuron 4 is most significant (glows brightest)  and it corresponds to label D then the predicted neuron is 4 and prediction is D.

`getMostSignificantNeuronNumAsPrediction()` is a method in LayerManager class which helps to get the value of the neuron which fires the most.

#### Restarting the file
Creates a new instance of the file reader and starts all over again.
```java
    public void restart()
````


### Examples

- Example code https://github.com/Aatmaj-Zephyr/ANN4j/blob/main/Main.java
- Sample output https://github.com/Aatmaj-Zephyr/ANN4j/blob/3721148ec24371bf095e1394fe39fc471f391466/output.txt

- Sample output https://github.com/Aatmaj-Zephyr/ANN4j/blob/ef0f34b505e6e6316f94b5a660b9ef651582667d/output.txt

#### Other resources

- More about Artificial Neural Networks https://www.3blue1brown.com/topics/neural-networks
- Relevance propagation example https://towardsdatascience.com/indepth-layer-wise-relevance-propagation-340f95deb1ea
- Rectification functions https://www.quora.com/What-is-the-purpose-of-rectifier-functions-in-neural-networks

### ANN4j Community

#### ⌨️ Raising an issue
Please feel free to suggest any changes or point out any errors by raising an issue [here](https://github.com/Aatmaj-Zephyr/ANN4j/issues/new/choose)

#### Asking for help

For asking for clarification on any topic, raise an question issue [here](https://github.com/Aatmaj-Zephyr/ANN4j/issues/new?assignees=Aatmaj-Zephyr&labels=&template=question.md&title=Question)

#### Community

- [Discussions](https://github.com/Aatmaj-Zephyr/ANN4j/discussions)
- [Wiki](https://github.com/Aatmaj-Zephyr/ANN4j/wiki)

#### Contributing
Please read the contributing guidelines [here](https://github.com/Aatmaj-Zephyr/ANN4j/blob/main/CONTRIBUTING.md).
Everyone is free to contribute to this project. 

#### Help spread the word
Are you using ANN4j in your research or project? If so, please let me know and I may add a link to your project or application and your logo to this repository. 

#### Citing this package for research work
You can cite this repository using the following bibtex entry. Please update the date.

```
@misc{AatmajZephyr21:online,
author = {Aatmaj Mhatre},
title = {Aatmaj-Zephyr/ANN4j: Artificial Neural Networks for Java This package provides Object oriented Neural Networks for making Explainable Networks. Object Oriented Network structure is helpful for observing each and every element the model. This package is developed for XAI research and development.},
howpublished = {\url{https://github.com/Aatmaj-Zephyr/ANN4j}},
month = {},
year = {},
note = {(Accessed on <date>)}
}
```

### License

[License notice](https://github.com/Aatmaj-Zephyr/ANN4j/blob/main/LICENSE)

```
MIT License

Copyright (c) 2022 Aatmaj

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
