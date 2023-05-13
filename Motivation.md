#### Why ANN4j? Why OOP?
One question might arise in your minds related to why the package is required. There are so many tried and tested tools and technologies that already are training neural networks in an effective manner. Then for what reason one must use a language like Java and a slow ineffective package made by some unknown person?
The answer to this question is because the purpose of ANN4j is very different from other libraries available in popular languages like python or R. ANN4j is not a tool that you should use for your next ML project. ANN4j does not aim at making neural networks effective to train neither does it strive for providing an industry standard interface. 
The purpose of ANN4j is solely to help researchers. ANN4j is a tool for performing Proof-Of-Concept research on neural networks. ANN4j makes it easy to design and test new algorithms on neural networks. This is possible due to the OOP concepts used in this package. The OOP concepts make it extremely easy for anyone to use, modify the Neural Network algorithms. This is not possible in other libraries. For example you cannot easily replace the backpropogation algorithm in Tenserflow with your custom algorothm.
Another important advantage of ANN4j is its Object Oriented implimentation. How does that help? ANN4j treats every neuron as an object, not as an element in a matrix. This means that if you want to access specific neurons, perform custom complicated algorithms on a specific set of neurons, you can easily do so. You wont need to translate it into a matrix calculation. 
Lets take example of forward propogation. 

Actual concept: 
![image](https://github.com/Aatmaj-Zephyr/ANN4j/assets/83284294/01249a20-7c3a-446b-9a83-05017ee40a63)


Matrix cacculation:
<img width="240" alt="image" src="https://github.com/Aatmaj-Zephyr/ANN4j/assets/83284294/f8d6a7c1-7ccb-4d1f-8224-c91c0402022b">



ANN4j implimentation:

``` java
 for (Neuron i : listOfNeurons) {
           
  for (Connection j : i.leftConnections) {
           
                sum += j.leftNeuron.getActivation() * this.weight;
            

        }
}
```

|        | Efficiant | Easy to Interpret | Easy to modify |
|--------|-----------|-------------------|----------------|
| Matrix | Yes       | No                | No             |
| ANN4j  | No        | Yes               | Yes            |
